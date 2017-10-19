package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.storage.Storage;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
class LoginChecker {

    private final Storage storage;

    @Autowired
    LoginChecker(Storage storage) {
        this.storage = storage;
    }

    boolean isLogged() {

        return hasCredentials() && hasCorrectCookies();
    }

    private boolean hasCredentials() {

        return isNotEmpty(storage.getUser()) && isNotEmpty(storage.getPassword());
    }

    private boolean hasCorrectCookies() {

        return newArrayList("LXToken", "__RequestVerificationToken_",
                "ASP.NET_SessionId", "LXCookieMonit")
                .stream()
                .allMatch(this::isInStorage);
    }

    private boolean isInStorage(String cookie) {

        return storage.getAuthorizationCookies().stream()
                .map(Cookie::getKey)
                .anyMatch(key -> key.startsWith(cookie));
    }
}
