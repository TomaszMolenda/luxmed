package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
class SecondStepExtractor {

    private final ConnectionService connectionService;
    private final RequestDataCreator requestDataCreator;
    private final DoctorExtractor doctorExtractor;
    private final PayerExtractor payerExtractor;

    @Autowired
    SecondStepExtractor(ConnectionService connectionService, RequestDataCreator requestDataCreator, DoctorExtractor doctorExtractor, PayerExtractor payerExtractor) {
        this.connectionService = connectionService;
        this.requestDataCreator = requestDataCreator;
        this.doctorExtractor = doctorExtractor;
        this.payerExtractor = payerExtractor;
    }

    public SecondStepFilter extract(List<Cookie> authorizationCookies, FirstStepFilterForm firstStepFilterForm) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .cookie(authorizationCookies)
                .data(requestDataCreator.create(firstStepFilterForm))
                .httpMethod(HttpMethod.POST)
                .build();

        Document document = connectionService.postForHtml(connectionRequest).getDocument();

        List<Doctor> doctors = doctorExtractor.extract(document);
        List<Payer> payers = payerExtractor.extract(document);

        return new SecondStepFilter(doctors, payers);
    }
}
