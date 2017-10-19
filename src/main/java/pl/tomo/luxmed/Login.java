package pl.tomo.luxmed;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Login {

    @Autowired private ConnectionService connectionService;

    void login() {

//        Cookie sessionId = fetchSessionId();

        List<Cookie> authorizationCookies = fetchAuthorizationCookies();

        ConnectionRequest connectionRequest1 = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Home/GetFilter")
                .httpMethod(HttpMethod.GET)
                .cookie(authorizationCookies)
//                .cookie(sessionId)
                .build();

        HtmlResponse htmlResponse1 = connectionService.getForHtml(connectionRequest1);

        System.out.println(htmlResponse1.getDocument());

    }

    private Cookie fetchSessionId() {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Account/LogOn")
                .httpMethod(HttpMethod.GET)
                .build();

        return connectionService.getForHtml(connectionRequest).fetchCookies().stream()
                .filter(cookie -> cookie.getKey().equals("ASP.NET_SessionId"))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    private List<Cookie> fetchAuthorizationCookies() {

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url("https://portalpacjenta.luxmed.pl/PatientPortal/Account/LogIn?Login=tomasz.molenda&Password=w6Kv7C1bXW")
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
