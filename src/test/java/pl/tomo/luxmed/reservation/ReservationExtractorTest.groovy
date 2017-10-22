package pl.tomo.luxmed.reservation

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import spock.lang.Specification

class ReservationExtractorTest extends Specification {

    def "Extract"() {

        given:
        ReservationExtractor reservationExtractor = new ReservationExtractor()

        InputStream inputStream = ReservationExtractorTest.class.getClassLoader().getResourceAsStream("reservation.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/Find")

        when:
        def reservations = reservationExtractor.extract(document)

        then:
        reservations.size() == 42
    }
}
