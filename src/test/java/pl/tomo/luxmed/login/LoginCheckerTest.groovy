package pl.tomo.luxmed.login

import pl.tomo.luxmed.connection.Cookie
import pl.tomo.luxmed.storage.Storage
import spock.lang.Specification

class LoginCheckerTest extends Specification {

    def "IsLogged"() {

        given:
        Storage storage = Mock()
        LoginChecker loginChecker = new LoginChecker(storage)

        storage.getUser() >> "a"
        storage.getPassword() >> "a"
        storage.authorizationCookies >> createCookies()

        when:
        def isLogged = loginChecker.isLogged()

        then:
        isLogged
    }

    def "shouldReturnNotLoggedWhenCookiesAreWrong"() {

        given:
        Storage storage = Mock()
        LoginChecker loginChecker = new LoginChecker(storage)

        storage.getUser() >> "a"
        storage.getPassword() >> "a"
        storage.authorizationCookies >> createWrongCookies()

        when:
        def isLogged = loginChecker.isLogged()

        then:
        !isLogged
    }

    def "shouldReturnNotLoggedWhenCredentialsAreWrong"() {

        given:
        Storage storage = Mock()
        LoginChecker loginChecker = new LoginChecker(storage)

        storage.getUser() >> ""
        storage.getPassword() >> "a"
        storage.authorizationCookies >> createCookies()

        when:
        def isLogged = loginChecker.isLogged()

        then:
        !isLogged
    }

    List<Cookie> createWrongCookies() {

        return [new Cookie("LXToken", "a")]
    }

    List<Cookie> createCookies() {

        return [new Cookie("LXToken", "a"), new Cookie("__RequestVerificationToken_dasda", "a"),
        new Cookie("ASP.NET_SessionId", "a"), new Cookie("LXCookieMonit", "a")]
    }
}