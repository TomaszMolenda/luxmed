package pl.tomo.luxmed.storage;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.tomo.luxmed.city.City;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.coordination.CoordinationActivity;
import pl.tomo.luxmed.mediservice.MediService;
import pl.tomo.luxmed.payers.Payer;
import pl.tomo.luxmed.reservation.filter.Filter;

import java.util.List;

@Data
@Component
public class Storage {

    private String user;
    private String password;
    private List<CoordinationActivity> coordinationActivities;
    private List<City> cities;
    private String cityId;
    private List<MediService> services;
    private String serviceId;
    private List<Payer> payers;
    private String payerId;
    private String requestVerificationToken;
    private boolean isReserved;
    private Filter filter = Filter.empty();
    private List<Log> logs = Lists.newArrayList();

    public void addLog(Log log) {

        logs.add(log);
    }
}
