package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.connection.HtmlResponse;

import java.util.List;

@Service
public class Login {

    private final ConnectionService connectionService;
    private final AuthorizationCookieFetcher authorizationCookieFetcher;

    @Autowired
    Login(ConnectionService connectionService, AuthorizationCookieFetcher authorizationCookieFetcher) {
        this.connectionService = connectionService;
        this.authorizationCookieFetcher = authorizationCookieFetcher;
    }

    void login() {

        List<Cookie> authorizationCookies = authorizationCookieFetcher.fetch();

        ConnectionRequest connectionRequest1 = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(authorizationCookies)
                .build();

        HtmlResponse htmlResponse1 = connectionService.getForHtml(connectionRequest1);

        System.out.println(htmlResponse1.getDocument());

    }

}
