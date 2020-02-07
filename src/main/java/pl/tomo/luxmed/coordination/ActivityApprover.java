package pl.tomo.luxmed.coordination;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.HtmlResponse;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
class ActivityApprover {

    private final Storage storage;
    private final ConnectionService connectionService;

    @Autowired
    ActivityApprover(Storage storage, ConnectionService connectionService) {
        this.storage = storage;
        this.connectionService = connectionService;
    }

    @SneakyThrows
    Document approve(String url) {

        storage.addLog(Log.log(getCoordinationActivity(url)));

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + url)
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        final HtmlResponse response = connectionService.getForHtml(connectionRequest).get();

        final Optional<String> urlInSubPage = checkSubPage(response.getDocument());

        if (urlInSubPage.isPresent()) {

            return approve(urlInSubPage.get());
        }

        String actionId = StringUtils.substringAfterLast(url, "actionId=");
        storage.setCoordinationActivityId(actionId);

        return goToLocation(response);
    }

    private String getCoordinationActivity(String url) {
        return storage.getCoordinationActivities().stream()
                .filter(coordinationActivity -> coordinationActivity.getUrl().equalsIgnoreCase(url))
                .map(CoordinationActivity::getName)
                .findAny()
                .orElse(EMPTY);
    }

    private Optional<String> checkSubPage(Document document) {

        return document.getElementsByClass("activity_button btn btn-default").stream()
                .filter(element -> element.text().equalsIgnoreCase("Wizyta w placówce"))
                .map(element -> element.attr("href"))
                .findAny();
    }

    @SneakyThrows
    private Document goToLocation(HtmlResponse response) {

        final String location = response.getHttpResponse().getHeaders().getLocation();

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + location)
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest).get();

        if (htmlResponse.obtainLocation() == null) {
            return htmlResponse.getDocument();
        } else {
            return goToLocation(htmlResponse);
        }
    }
}
