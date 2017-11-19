package pl.tomo.luxmed.storage;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomo.luxmed.city.City;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.mediservice.MediService;

import java.util.List;

@Data
@Component
public class Storage {

    private String user;
    private String password;
    private List<Cookie> authorizationCookies;
    private List<City> cities;
    private String cityId;
    private List<Clinic> clinics;
    private List<MediService> services;
    private String serviceId;
    private List<Doctor> doctors;
    private List<Payer> payers;
    private String requestVerificationToken;
    private boolean isReserved;
}
