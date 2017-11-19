package pl.tomo.luxmed.mediservice

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import spock.lang.Specification

class ServiceExtractorTest extends Specification {

    def "Extract"() {

        given:
        ServiceExtractor serviceExtractor = new ServiceExtractor()

        InputStream inputStream = ServiceExtractorTest.class.getClassLoader().getResourceAsStream("services.html")
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/GetFilter")

        when:
        def services = serviceExtractor.extract(document)

        then:
        services.size() == 1
    }
}
