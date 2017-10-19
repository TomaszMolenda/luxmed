package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;
import pl.tomo.luxmed.storage.Storage;

import java.time.LocalDate;
import java.util.List;

@Service
public class Login {

    private final Storage storage;
    private final FilterDataExtractor filterDataExtractor;
    private final ReservationFetcher reservationFetcher;
    private final ReservationExecutor reservationExecutor;

    @Autowired
    Login(Storage storage, FilterDataExtractor filterDataExtractor, ReservationFetcher reservationFetcher, ReservationExecutor reservationExecutor) {
        this.storage = storage;
        this.filterDataExtractor = filterDataExtractor;
        this.reservationFetcher = reservationFetcher;
        this.reservationExecutor = reservationExecutor;
    }

    void login() {

        List<Cookie> authorizationCookies = storage.getAuthorizationCookies();

        FilterForm filterForm = new FilterForm();
        filterForm.setCityId("5");
        filterForm.setServiceId("4502");
        filterForm.setDateFrom(LocalDate.now());

        SecondStepFilter secondStepFilter = filterDataExtractor.extractSecondStep(authorizationCookies, filterForm);

        FilterForm secondStepFilterForm = new FilterForm();

        secondStepFilterForm.setIsFromStartPage("True");
        secondStepFilterForm.setPayersCount("0");
        secondStepFilterForm.setSearchFirstFree("True");
        secondStepFilterForm.setTimeOption("Any");
        secondStepFilterForm.setCityId("5");
        secondStepFilterForm.setServiceId("4502");
        secondStepFilterForm.setDateFrom(LocalDate.now());
        secondStepFilterForm.setToDate(LocalDate.now());
        secondStepFilterForm.setPayerId("106357");
        secondStepFilterForm.setRequestVerificationToken(secondStepFilter.getRequestVerificationToken());

        List<Reservation> reservations = reservationFetcher.fetch(authorizationCookies, secondStepFilterForm);

        Reservation reservation = reservations.get(0);

        reservationExecutor.reserve(reservation, authorizationCookies);
    }

}
