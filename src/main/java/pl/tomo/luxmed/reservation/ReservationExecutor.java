package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationExecutor {

    private final ConnectionService connectionService;
    private final ReservationKeyRetriever reservationKeyRetriever;
    private final Storage storage;
    private final ReservationChecker reservationChecker;

    @Autowired
    ReservationExecutor(ConnectionService connectionService, ReservationKeyRetriever reservationKeyRetriever, Storage storage, ReservationChecker reservationChecker) {
        this.connectionService = connectionService;
        this.reservationKeyRetriever = reservationKeyRetriever;
        this.storage = storage;
        this.reservationChecker = reservationChecker;
    }

    @SneakyThrows
    boolean reserve(Reservation reservation) {

        String key = reservationKeyRetriever.retrieve(reservation);

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReserveTerm?key=" + key + "&variant=1")
                .httpMethod(HttpMethod.POST)
                .cookie(storage.getAuthorizationCookies())
                .build();

        Document document = connectionService.postForHtml(connectionRequest)
                .get()
                .getDocument();

        return reservationChecker.checkSuccess(document);
    }
}