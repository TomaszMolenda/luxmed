package pl.tomo.luxmed.login;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.ConnectionRequest;
import pl.tomo.luxmed.connection.ConnectionService;

@Service
class AuthorizationMaker {

    private final ConnectionService connectionService;

    @Autowired
    AuthorizationMaker(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @SneakyThrows
    void logIn(LoginForm loginForm) {

        final String url = "https://portalpacjenta.luxmed.pl/PatientPortal/Account/LogIn?Login=" +
                loginForm.getUser() + "&Password=" + loginForm.getPassword();

        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .url(url)
                .httpMethod(HttpMethod.GET)
                .build();

        connectionService.getForHtml(connectionRequest);
    }
}
