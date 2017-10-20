package pl.tomo.luxmed

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import pl.tomo.luxmed.service.ReservationKeyExtractor
import spock.lang.Specification

class ReservationKeyExtractorTest extends Specification {

    def "Extract"() {

        given:
        ReservationKeyExtractor reservationKeyExtractor = new ReservationKeyExtractor()

        InputStream inputStream = ReservationKeyExtractor.class.getClassLoader().getResourceAsStream("reservation-key.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/ReservationConfirmation")

        when:
        def key = reservationKeyExtractor.extract(document)

        then:
        key == "55248453-5675-4cb4-81ce-4218eac0f318"
    }
}
