package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
public class Login {


    private final AuthorizationCookieFetcher authorizationCookieFetcher;
    private final FilterDataExtractor filterDataExtractor;

    @Autowired
    Login(AuthorizationCookieFetcher authorizationCookieFetcher, FilterDataExtractor filterDataExtractor) {
        this.authorizationCookieFetcher = authorizationCookieFetcher;
        this.filterDataExtractor = filterDataExtractor;
    }

    void login() {

        List<Cookie> authorizationCookies = authorizationCookieFetcher.fetch();
        FilterData filterData = filterDataExtractor.extract(authorizationCookies);

    }

}
