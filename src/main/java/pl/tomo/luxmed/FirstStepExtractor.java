package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.connection.HtmlResponse;

import java.util.List;

@Service
class FirstStepExtractor {

    private final ConnectionService connectionService;
    private final ClinicExtractor clinicExtractor;
    private final CityExtractor cityExtractor;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    FirstStepExtractor(ConnectionService connectionService, ClinicExtractor clinicExtractor,
                       CityExtractor cityExtractor, ServiceExtractor serviceExtractor) {
        this.connectionService = connectionService;
        this.clinicExtractor = clinicExtractor;
        this.cityExtractor = cityExtractor;
        this.serviceExtractor = serviceExtractor;
    }

    FirstStepFilter extract(List<Cookie> authorizationCookies) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(authorizationCookies)
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest);

        Document document = htmlResponse.getDocument();

        List<Clinic> clinics = clinicExtractor.extract(document);
        List<City> cities = cityExtractor.extract(document);
        List<MediService> mediServices = serviceExtractor.extract(document);

        return new FirstStepFilter(clinics, cities, mediServices);
    }

}
