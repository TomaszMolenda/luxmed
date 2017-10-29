package pl.tomo.luxmed.service;

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
public class SimpleDataFetcher {

    private final ConnectionService connectionService;
    private final Storage storage;

    @Autowired
    SimpleDataFetcher(ConnectionService connectionService, Storage storage) {
        this.connectionService = connectionService;
        this.storage = storage;
    }

    @SneakyThrows
    public Document extract() {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Coordination")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest).get().getToLocation();

        return goToLocation(htmlResponse);
    }

    @SneakyThrows
    private Document goToLocation(HtmlResponse htmlResponse) {

        String location = htmlResponse.obtainLocation();

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Coordination/Activity?actionId=90")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse response = connectionService.getForHtml(connectionRequest).get();

        return goToLocation1(response);
    }

    @SneakyThrows
    private Document goToLocation1(HtmlResponse htmlResponse) {

        String location = htmlResponse.obtainLocation();

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + location)
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse response = connectionService.getForHtml(connectionRequest).get();

        return response.getDocument();
    }

}
