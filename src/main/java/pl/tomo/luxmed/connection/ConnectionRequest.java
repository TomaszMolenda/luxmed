package pl.tomo.luxmed.connection;

import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
public class ConnectionRequest
{
    String url;
    @Singular("data")
    List<DataEntry> data;
    @Singular("cookie")
    List<Cookie> cookie;
    HttpMethod httpMethod;

    void addCookie(Set<Cookie> cookies) {
        if (Objects.isNull(cookie)) {
            cookie = Lists.newArrayList(cookies);
        }
        else {
            ArrayList<Cookie> existingCookies = Lists.newArrayList(cookie);
            existingCookies.addAll(cookies);
            cookie = existingCookies;
        }
    }
}
