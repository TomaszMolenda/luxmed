package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

@Service
class LoginFormSaver {

    private final Storage storage;

    @Autowired
    LoginFormSaver(Storage storage) {
        this.storage = storage;
    }

    void save(LoginForm loginForm) {

        storage.setUser(loginForm.getUser());
        storage.setPassword(loginForm.getPassword());
    }
}
