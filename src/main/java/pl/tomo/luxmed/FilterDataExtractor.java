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
class FilterDataExtractor {

    private final ConnectionService connectionService;
    private final ClinicExtractor clinicExtractor;

    @Autowired
    FilterDataExtractor(ConnectionService connectionService, ClinicExtractor clinicExtractor) {
        this.connectionService = connectionService;
        this.clinicExtractor = clinicExtractor;
    }

    FilterData extract(List<Cookie> authorizationCookies) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(authorizationCookies)
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest);

        Document document = htmlResponse.getDocument();

        List<Clinic> clinics = clinicExtractor.extract(document);

        return null;
    }


}
