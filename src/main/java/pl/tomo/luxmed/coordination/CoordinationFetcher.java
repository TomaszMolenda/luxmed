package pl.tomo.luxmed.coordination;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CoordinationFetcher {

    private final ConnectionService connectionService;
    private final Storage storage;
    private final CoordinationActivityFactory coordinationActivityFactory;

    @Autowired
    CoordinationFetcher(ConnectionService connectionService, Storage storage, CoordinationActivityFactory coordinationActivityFactory) {
        this.connectionService = connectionService;
        this.storage = storage;
        this.coordinationActivityFactory = coordinationActivityFactory;
    }

    List<CoordinationActivity> fetchActivities() {

        return obtainHtml().getElementsByClass("activity_button")
                .stream()
                .map(coordinationActivityFactory::create)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private Document obtainHtml() {

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Coordination")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        return connectionService.getForHtml(connectionRequest).get().getDocument();
    }
}
