package pl.tomo.luxmed.reservation

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import spock.lang.Specification

class ReservationStatusCheckerTest extends Specification {

    def "CheckSuccess"() {

        given:
        ReservationStatusChecker reservationChecker = new ReservationStatusChecker()

        InputStream inputStream = ReservationKeyExtractor.class.getClassLoader().getResourceAsStream("reservation-success.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReservationConfirmation")

        when:
        def success = reservationChecker.checkSuccess(document)

        then:
        success
    }
}
