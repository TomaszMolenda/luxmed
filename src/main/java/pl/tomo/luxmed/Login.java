package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;

import java.time.LocalDate;
import java.util.List;

@Service
public class Login {


    private final AuthorizationCookieFetcher authorizationCookieFetcher;
    private final FilterDataExtractor filterDataExtractor;
    private final ReservationFetcher reservationFetcher;

    @Autowired
    Login(AuthorizationCookieFetcher authorizationCookieFetcher, FilterDataExtractor filterDataExtractor, ReservationFetcher reservationFetcher) {
        this.authorizationCookieFetcher = authorizationCookieFetcher;
        this.filterDataExtractor = filterDataExtractor;
        this.reservationFetcher = reservationFetcher;
    }

    void login() {

        List<Cookie> authorizationCookies = authorizationCookieFetcher.fetch();
        FirstStepFilter firstStepFilter = filterDataExtractor.extractFirstStep(authorizationCookies);

        FirstStepFilterForm firstStepFilterForm = new FirstStepFilterForm();
        firstStepFilterForm.setCityId("5");
        firstStepFilterForm.setServiceId("4502");
        firstStepFilterForm.setDateFrom(LocalDate.now());

        SecondStepFilter secondStepFilter = filterDataExtractor.extractSecondStep(authorizationCookies, firstStepFilterForm);

        List<Reservation> =

        :True
        :0
        :True
        :JZBHEWy1E1LlzxT35nCWE6pgm0z4Ji5aU8eO055_ct5BOAsSql2ImakVhgHALmLRie-C8Sykujf0W2_gxiNMN3lFHrksMtxMwJxgZZWR98vIXWsx5gAHTAtuLQBYHvR3YJ62yFWrwJU0CGv-8vacAMphkhkapDbolpKOQtUCipQ1
        :Any
        CityId:5
        ClinicId:
        ServiceId:4502
        FromDate:19-10-2017
        ToDate:19-10-2018
        PayerId:106357

    }

}
