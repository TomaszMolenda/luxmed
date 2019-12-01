package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationConfirmer {

    private final ConnectionService connectionService;
    private final ReservationKeyRetriever reservationKeyRetriever;
    private final Storage storage;

    @Autowired
    ReservationConfirmer(ConnectionService connectionService, ReservationKeyRetriever reservationKeyRetriever, Storage storage) {
        this.connectionService = connectionService;
        this.reservationKeyRetriever = reservationKeyRetriever;
        this.storage = storage;
    }

    @SneakyThrows
    Document confirm(Reservation reservation) {

        String key = reservationKeyRetriever.retrieve(reservation);

        storage.addLog(Log.log("Try confirm reservation: " + reservation));

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReserveTerm?key=" + key + "&variant=1")
                .httpMethod(HttpMethod.POST)
                .build();

        return connectionService.postForHtml(connectionRequest)
                .getDocument();
    }
}
