package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.DataEntry;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationKeyRetriever {

    private final ConnectionService connectionService;
    private final ReservationKeyExtractor reservationKeyExtractor;

    @Autowired
    ReservationKeyRetriever(ConnectionService connectionService, ReservationKeyExtractor reservationKeyExtractor) {
        this.connectionService = connectionService;
        this.reservationKeyExtractor = reservationKeyExtractor;
    }

    @SneakyThrows
    String retrieve(Reservation reservation) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReservationConfirmation")
                .data(new DataEntry("termId", reservation.getTermId()))
                .httpMethod(HttpMethod.POST)
                .build();

        Document document = connectionService.postForHtml(connectionRequest)
                .getDocument();

        return reservationKeyExtractor.extract(document);
    }
}
