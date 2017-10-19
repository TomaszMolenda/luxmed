package pl.tomo.luxmed.storage;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Data
@Component
public class Storage {

    private String user;
    private String password;
    private List<Cookie> authorizationCookies;
}
