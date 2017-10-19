package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import pl.tomo.luxmed.connection.Cookie;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Builder
@Data
public class HtmlResponse
{
    final HttpResponse httpResponse;
    final Document document;

    public List<Cookie> fetchCookies() {

        HttpHeaders headers = httpResponse.getHeaders();

        List<String> cookies = (List<String>) headers.get("set-cookie");

        return cookies.stream()
                .map(this::createCookies)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Cookie> createCookies(String cookies) {

        return Lists.newArrayList(Splitter.on(";").split(cookies))
                .stream()
                .map(String::trim)
                .filter(cookie -> !cookie.equalsIgnoreCase("path=/"))
                .filter(cookie -> !cookie.equalsIgnoreCase("HttpOnly"))
                .map(this::createCookie)
                .collect(Collectors.toList());
    }

    private Cookie createCookie(String cookie) {

        String[] strings = StringUtils.split(cookie, "=");

        if (strings.length == 0) {

            throw new RuntimeException();
        }

        if (strings.length == 2) {

            return new Cookie(strings[0], strings[1]);
        }

        if (strings.length == 1) {

            return new Cookie(strings[0], EMPTY);
        }

        throw new RuntimeException();
    }

    public String obtainLocation() {

        return httpResponse.getHeaders().getLocation();
    }
}
