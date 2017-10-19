package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
class ReservationFetcher {

    private final ConnectionService connectionService;
    private final RequestDataCreator requestDataCreator;

    @Autowired
    ReservationFetcher(ConnectionService connectionService, RequestDataCreator requestDataCreator) {
        this.connectionService = connectionService;
        this.requestDataCreator = requestDataCreator;
    }

    List<Reservation> fetch(List<Cookie> authorizationCookies, FilterForm filterForm) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/Find")
                .httpMethod(HttpMethod.POST)
                .cookie(authorizationCookies)
                .data(requestDataCreator.create(filterForm))
                .build();

        Document document = connectionService.postForHtml(connectionRequest).getDocument();

        System.out.println(document);

        return null;
    }
}
