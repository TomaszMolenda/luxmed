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

import static pl.tomo.luxmed.LuxmedApplication.URL;

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

    void fetchActivities() {

        final List<CoordinationActivity> coordinationActivities = obtainHtml().getElementsByClass("activity_button")
                .stream()
                .map(coordinationActivityFactory::create)
                .collect(Collectors.toList());

        storage.setCoordinationActivities(coordinationActivities);
    }

    @SneakyThrows
    private Document obtainHtml() {

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url(URL + "/PatientPortal/Reservations/Coordination")
                .httpMethod(HttpMethod.GET)
                .build();

        return connectionService.getForHtml(connectionRequest).getDocument();
    }
}
