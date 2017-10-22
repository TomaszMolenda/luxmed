package pl.tomo.luxmed.service;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

@Service
public class AdvancedDataFetcher {

    private final ConnectionService connectionService;
    private final RequestDataCreator requestDataCreator;
    private final Storage storage;

    @Autowired
    AdvancedDataFetcher(ConnectionService connectionService,
                        RequestDataCreator requestDataCreator,
                        Storage storage) {
        this.connectionService = connectionService;
        this.requestDataCreator = requestDataCreator;
        this.storage = storage;
    }

    @SneakyThrows
    public Document extract(FilterForm filterForm) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .cookie(storage.getAuthorizationCookies())
                .data(requestDataCreator.create(filterForm))
                .httpMethod(HttpMethod.POST)
                .build();

        return connectionService.postForHtml(connectionRequest).get().getDocument();
    }
}
