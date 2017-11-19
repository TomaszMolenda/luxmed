package pl.tomo.luxmed.coordination;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.HtmlResponse;
import pl.tomo.luxmed.storage.Storage;

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

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Coordination/Activity?actionId=81")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        final HtmlResponse response = connectionService.getForHtml(connectionRequest).get();

        return goToLocation(response);
    }

    @SneakyThrows
    private Document goToLocation(HtmlResponse response) {

        final String location = response.getHttpResponse().getHeaders().getLocation();

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + location)
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        return connectionService.getForHtml(connectionRequest).get().getDocument();
    }
}
