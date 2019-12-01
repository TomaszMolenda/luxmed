package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

import static pl.tomo.luxmed.storage.Log.log;

@Service
class UserInformationSaver {

    private final LoginFormSaver loginFormSaver;
    private final AuthorizationMaker authorizationMaker;
    private final Storage storage;

    @Autowired
    UserInformationSaver(LoginFormSaver loginFormSaver, AuthorizationMaker authorizationMaker, Storage storage) {
        this.loginFormSaver = loginFormSaver;
        this.authorizationMaker = authorizationMaker;
        this.storage = storage;
    }

    void save(LoginForm loginForm) {

        loginFormSaver.save(loginForm);
        authorizationMaker.logIn(loginForm);
        storage.addLog(log("Success login: " + loginForm.getUser()));
    }
}
