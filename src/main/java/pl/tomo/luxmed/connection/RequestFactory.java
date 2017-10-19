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
                request.getHttpMethod().name(), new GenericUrl(request.getUrl()), content);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setCookie(convertCookies(request.getCookie()));

        httpRequest.setHeaders(httpHeaders);

        httpRequest.setFollowRedirects(false);
        httpRequest.setThrowExceptionOnExecuteError(false);

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
