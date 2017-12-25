package pl.tomo.luxmed.reservation

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import pl.tomo.luxmed.storage.Storage
import spock.lang.Specification

class ReservationStatusUpdaterTest extends Specification {

    def "CheckSuccess"() {

        Storage storage = Mock()


        given:
        ReservationStatusUpdater reservationChecker = new ReservationStatusUpdater(storage, reservationErrorStorage)

        InputStream inputStream = ReservationKeyExtractor.class.getClassLoader().getResourceAsStream("reservation-success.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReservationConfirmation")

        when:
        reservationChecker.update(reservation, document)

        then:
        success
    }
}
