package pl.tomo.luxmed.first;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.HtmlResponse;
import pl.tomo.luxmed.storage.Storage;

@Service
class FirstStepViewModelCreator {

    private final ConnectionService connectionService;
    private final Storage storage;

    @Autowired
    FirstStepViewModelCreator(ConnectionService connectionService, Storage storage) {
        this.connectionService = connectionService;
        this.storage = storage;
    }

    Document extract() {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest);

        return htmlResponse.getDocument();
    }

}
