package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationRemover {

    private final ConnectionService connectionService;
    private final Storage storage;

    @Autowired
    ReservationRemover(ConnectionService connectionService, Storage storage) {
        this.connectionService = connectionService;
        this.storage = storage;
    }

    @SneakyThrows
    void remove(ReservationStatus reservationStatus) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Agreed/CancelTerm?ReservationId=" + reservationStatus.getId())
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        connectionService.getForHtml(connectionRequest);
    }
}
