package pl.tomo.luxmed.reservation;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.storage.Storage;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
class ReservationChecker {

    private final ConnectionService connectionService;
    private final Storage storage;

    @Autowired
    ReservationChecker(ConnectionService connectionService, Storage storage) {
        this.connectionService = connectionService;
        this.storage = storage;
    }

    @SneakyThrows
    ReservationStatus checkIfIsReservation(Reservation reservation) {

        Document document = fetchDocument();

        boolean hasReservationOnService = hasReservationOnService(reservation, document);

        if (hasReservationOnService) {

            String onClick = allReservations(document).getElementsByClass("button reject")
                    .first()
                    .attr("onclick");

            String id = StringUtils.substringBetween(onClick, "cancelAgreed(", ", '");

            return new ReservationStatus(hasReservationOnService, id);
        }

        return new ReservationStatus(hasReservationOnService, EMPTY);
    }

    private boolean hasReservationOnService(Reservation reservation, Document document) {

        return allReservations(document)
                    .getElementsByClass("description")
                    .stream()
                    .filter(element -> element.hasAttr("style"))
                    .map(element -> element.getElementsByTag("p").first())
                    .map(Element::text)
                    .anyMatch(text -> text.startsWith(reservation.getService()));
    }

    private Element allReservations(Document document) {

        return document.getElementById("divReservedDynamicContent");
    }

    private Document fetchDocument() throws InterruptedException, java.util.concurrent.ExecutionException {

        final ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Visits")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        return connectionService.getForHtml(connectionRequest)
                .get()
                .getDocument();
    }
}
