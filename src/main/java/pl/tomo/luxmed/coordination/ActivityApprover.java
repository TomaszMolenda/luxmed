package pl.tomo.luxmed.coordination;

import lombok.SneakyThrows;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.HtmlResponse;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

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
    List<CoordinationActivity> approve(String url) {

        storage.addLog(Log.log(getCoordinationActivity(url)));

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + url)
                .httpMethod(HttpMethod.GET)
                .build();

        final HtmlResponse response = connectionService.getForHtml(connectionRequest);

        List<CoordinationActivity> coordinationActivities = response.getDocument()
                .getElementsByClass("activity_button btn btn-default")
                .stream()
                .map(this::createCoordinationActivity)
                .collect(Collectors.toList());

        storage.setCoordinationActivities(coordinationActivities);

        return coordinationActivities;
    }

    private CoordinationActivity createCoordinationActivity(Element element) {

        String url = element.attr("href");
        String name = element.text();

        String headerText = element.parents()
                .stream()
                .filter(element1 -> element1.className().equalsIgnoreCase("row path_container"))
                .findAny()
                .map(Element::previousElementSibling)
                .map(element1 -> element1.getElementsByClass("pathHeader"))
                .map(this::getFirst)
                .map(Element::text)
                .orElse("");

        return new CoordinationActivity(headerText + name, url);
    }

    private Element getFirst(Elements elements) {

        return elements.stream()
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private String getCoordinationActivity(String url) {
        return storage.getCoordinationActivities().stream()
                .filter(coordinationActivity -> coordinationActivity.getUrl().equalsIgnoreCase(url))
                .map(CoordinationActivity::getName)
                .findAny()
                .orElse(EMPTY);
    }
}
