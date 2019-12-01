package pl.tomo.luxmed.city;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

import static pl.tomo.luxmed.LuxmedApplication.URL;

@Service
class CitySaver {

    private final CityExtractor cityExtractor;
    private final Storage storage;
    private final ConnectionService connectionService;

    @Autowired
    CitySaver(CityExtractor cityExtractor, Storage storage, ConnectionService connectionService) {
        this.cityExtractor = cityExtractor;
        this.storage = storage;
        this.connectionService = connectionService;
    }

    void save(String activityId) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url(URL + "/PatientPortal/Reservations/Reservation/Coordination?activityId=" + activityId)
                .httpMethod(HttpMethod.GET)
                .build();

        Document document = connectionService.getForHtml(connectionRequest)
                .getDocument();

        final List<City> cities = cityExtractor.extract(document);

        storage.setCities(cities);
    }
}
