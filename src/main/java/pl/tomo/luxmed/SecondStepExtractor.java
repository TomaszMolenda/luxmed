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
    private final RequestVerificationTokenExtractor requestVerificationTokenExtractor;

    @Autowired
    SecondStepExtractor(ConnectionService connectionService, RequestDataCreator requestDataCreator, DoctorExtractor doctorExtractor, PayerExtractor payerExtractor, RequestVerificationTokenExtractor requestVerificationTokenExtractor) {
        this.connectionService = connectionService;
        this.requestDataCreator = requestDataCreator;
        this.doctorExtractor = doctorExtractor;
        this.payerExtractor = payerExtractor;
        this.requestVerificationTokenExtractor = requestVerificationTokenExtractor;
    }

    SecondStepFilter extract(List<Cookie> authorizationCookies, FilterForm filterForm) {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .cookie(authorizationCookies)
                .data(requestDataCreator.create(filterForm))
                .httpMethod(HttpMethod.POST)
                .build();

        Document document = connectionService.postForHtml(connectionRequest).getDocument();

        final List<Doctor> doctors = doctorExtractor.extract(document);
        final List<Payer> payers = payerExtractor.extract(document);

        final String requestVerificationToken = requestVerificationTokenExtractor.extract(document);

        return new SecondStepFilter(doctors, payers, requestVerificationToken);
    }
}
