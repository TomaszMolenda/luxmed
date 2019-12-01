package pl.tomo.luxmed.connection;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CookiePersistor {

    private Set<Cookie> authorizationCookies = Sets.newHashSet();

    public void save(List<Cookie> cookies) {

        authorizationCookies.addAll(cookies);
    }

    public Set<Cookie> getCookies() {
        return authorizationCookies;
    }
}
