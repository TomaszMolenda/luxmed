package pl.tomo.luxmed.connection;

import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class RequestFactory {

    static String ENCODING = "gzip, deflate, br";

    private HttpRequestFactory apacheBasedFactory;

    @Autowired
    RequestFactory(ApacheHttpTransport apacheHttpTransport) {

        apacheBasedFactory = apacheHttpTransport.createRequestFactory();
    }

    @SneakyThrows
    HttpRequest buildRequest(ConnectionRequest request, HttpContent content) {

        HttpRequest httpRequest = apacheBasedFactory.buildRequest(
                request.getHttpMethod().name(), new GenericUrl(request.getUrl()), null);

//        HttpHeaders httpHeaders = new HttpHeaders();

//        httpHeaders.set(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
//        httpHeaders.setAcceptEncoding(ENCODING);
//        httpHeaders.set("Accept-Language", "pl,en-US;q=0.8,en;q=0.6,ru;q=0.4");
//        httpHeaders.set("Cache-Control", "max-age=0");
//        httpHeaders.set("Content-Length", "40");
//        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded");
//        httpHeaders.set("Host", "portalpacjenta.luxmed.pl");
//        httpHeaders.set("Origin", "https://portalpacjenta.luxmed.pl");
//        httpHeaders.set("Referer", "https://portalpacjenta.luxmed.pl/PatientPortal/Account/LogOn");
//        httpHeaders.set("Upgrade-Insecure-Requests", "1");
//        httpHeaders.setCookie("_ga=GA1.2.576440155.1508336869; _gid=GA1.2.1675062906.1508336869; ASP.NET_SessionId=n2dkorcmwa4luqlrvb0pteqf; LXCookieMonit=1; GlobalLang=pl; __RequestVerificationToken_L1BhdGllbnRQb3J0YWw1=9400WDjmqQJtfZ0hHFJzIRDqurlvcsIfljZlu-oVGKGUQQVl2k2WdqXOqepy0sfceiTDeO3Gp0VDvVo2UVarxf2Px4OJg3nR5yXBPcCFjrlNeh9iw5FuJFpJkV2-WrAfLrifM9NO5e-3S1D-y7H0lw2; _gat=1");
//        httpHeaders.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");



//        httpRequest.setHeaders(httpHeaders);
//        httpRequest.setFollowRedirects(true);
//        httpRequest.setThrowExceptionOnExecuteError(false);

        return httpRequest;
    }

    @SneakyThrows
    HttpRequest buildRequest(ConnectionRequest request) {

        HttpRequest httpRequest = apacheBasedFactory.buildRequest(
                request.getHttpMethod().name(), new GenericUrl(request.getUrl()), null);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setCookie(convertCookies(request.getCookie()));

        httpRequest.setHeaders(httpHeaders);

        httpRequest.setFollowRedirects(false);
        httpRequest.setThrowExceptionOnExecuteError(false);

        return httpRequest;
    }

    private String convertCookies(List<Cookie> cookies) {

        return cookies.stream()
                .map(this::convertCookie)
                .collect(Collectors.joining("; "));
    }

    private String convertCookie(Cookie cookie) {

        return cookie.getKey() + "=" + cookie.getValue();
    }
}
