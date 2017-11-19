package pl.tomo.luxmed.city

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import spock.lang.Specification

class CityExtractorTest extends Specification {

    def "Extract"() {

        given:
        CityExtractor cityExtractor = new CityExtractor()

        InputStream inputStream = CityExtractorTest.class.getClassLoader().getResourceAsStream("cities.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/Find?firstTry=true")

        when:
        def reservations = cityExtractor.extract(document)

        then:
        reservations.size() == 26
    }
}
