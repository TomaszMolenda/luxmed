package pl.tomo.luxmed.login;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.connection.HtmlResponse;

import java.util.List;
import java.util.Set;

@Service
class AuthorizationCookieFetcher {

    private final ConnectionService connectionService;

    @Autowired
    AuthorizationCookieFetcher(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    List<Cookie> fetch(LoginForm loginForm) {

        final String url = "https://portalpacjenta.luxmed.pl/PatientPortal/Account/LogIn?Login=" +
                loginForm.getUser() + "&Password=" + loginForm.getPassword();

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url(url)
                .httpMethod(HttpMethod.GET)
                .build();

        HtmlResponse htmlResponse = connectionService.getForHtml(connectionRequest);

        return goToLocation(htmlResponse);
    }

    private List<Cookie> goToLocation(HtmlResponse htmlResponse) {

        List<Cookie> cookies = htmlResponse.fetchCookies();

        String location = htmlResponse.obtainLocation();

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl" + location)
                .httpMethod(HttpMethod.GET)
                .cookie(cookies)
                .build();

        Set<Cookie> newCookies = Sets.newHashSet(connectionService.getForHtml(connectionRequest).fetchCookies());

        newCookies.addAll(cookies);

        return Lists.newArrayList(newCookies);
    }

}
