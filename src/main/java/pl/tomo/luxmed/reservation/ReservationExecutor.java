package pl.tomo.luxmed.reservation;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
class ReservationExecutor {

    private final ConnectionService connectionService;
    private final ReservationKeyRetriever reservationKeyRetriever;

    @Autowired
    ReservationExecutor(ConnectionService connectionService, ReservationKeyRetriever reservationKeyRetriever) {
        this.connectionService = connectionService;
        this.reservationKeyRetriever = reservationKeyRetriever;
    }

    public void reserve(Reservation reservation, List<Cookie> authorizationCookies) {

        String key = reservationKeyRetriever.retrieve(reservation, authorizationCookies);

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReserveTerm?key=" + key + "&variant=1")
                .httpMethod(HttpMethod.POST)
                .cookie(authorizationCookies)
                .build();

        Document document = connectionService.postForHtml(connectionRequest).getDocument();
    }
}
