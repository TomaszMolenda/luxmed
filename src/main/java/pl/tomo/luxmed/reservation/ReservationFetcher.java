package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.service.FilterForm;
import pl.tomo.luxmed.service.RequestDataCreator;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
class ReservationFetcher {

    private final ConnectionService connectionService;
    private final RequestDataCreator requestDataCreator;
    private final ReservationExtractor reservationExtractor;
    private final Storage storage;

    @Autowired
    ReservationFetcher(ConnectionService connectionService, RequestDataCreator requestDataCreator, ReservationExtractor reservationExtractor, Storage storage) {
        this.connectionService = connectionService;
        this.requestDataCreator = requestDataCreator;
        this.reservationExtractor = reservationExtractor;
        this.storage = storage;
    }

    @SneakyThrows
    List<Reservation> fetch(FilterForm filterForm) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/PartialSearch")
                .httpMethod(HttpMethod.POST)
                .cookie(storage.getAuthorizationCookies())
                .data(requestDataCreator.create(filterForm))
                .build();

        Document document = connectionService.postForHtml(connectionRequest)
                .get()
                .getDocument();

        return reservationExtractor.extract(document);
    }
}
