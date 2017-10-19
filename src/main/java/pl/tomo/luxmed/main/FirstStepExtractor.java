package pl.tomo.luxmed.main;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.HtmlResponse;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
class FirstStepExtractor {

    private final ConnectionService connectionService;
    private final ClinicExtractor clinicExtractor;
    private final CityExtractor cityExtractor;
    private final ServiceExtractor serviceExtractor;
    private final Storage storage;

    @Autowired
    FirstStepExtractor(ConnectionService connectionService, ClinicExtractor clinicExtractor,
                       CityExtractor cityExtractor, ServiceExtractor serviceExtractor, Storage storage) {
        this.connectionService = connectionService;
        this.clinicExtractor = clinicExtractor;
        this.cityExtractor = cityExtractor;
        this.serviceExtractor = serviceExtractor;
        this.storage = storage;
    }

    FirstStepFilter extract() {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(storage.getAuthorizationCookies())
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest);

        Document document = htmlResponse.getDocument();

        List<Clinic> clinics = clinicExtractor.extract(document);
        List<City> cities = cityExtractor.extract(document);
        List<MediService> mediServices = serviceExtractor.extract(document);

        return new FirstStepFilter(clinics, cities, mediServices);
    }

}
