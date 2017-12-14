package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

import static pl.tomo.luxmed.storage.Log.log;

@Service
class UserInformationSaver {

    private final LoginFormSaver loginFormSaver;
    private final AuthorizationCookieFetcher authorizationCookieFetcher;
    private final Storage storage;

    @Autowired
    UserInformationSaver(LoginFormSaver loginFormSaver, AuthorizationCookieFetcher authorizationCookieFetcher, Storage storage) {
        this.loginFormSaver = loginFormSaver;
        this.authorizationCookieFetcher = authorizationCookieFetcher;
        this.storage = storage;
    }

    void save(LoginForm loginForm) {

        loginFormSaver.save(loginForm);
        storage.setAuthorizationCookies(authorizationCookieFetcher.fetch(loginForm));
        storage.addLog(log("Success login: " + loginForm.getUser()));
    }
}
