package pl.tomo.luxmed.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.FilterForm;
import pl.tomo.luxmed.storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class ReservationStarter {

    private final ReservationFetcher reservationFetcher;
    private final ReservationExecutor reservationExecutor;
    private final ReservationFilterExecutor reservationFilterExecutor;
    private final Storage storage;


    @Autowired
    ReservationStarter(ReservationFetcher reservationFetcher, ReservationExecutor reservationExecutor, ReservationFilterExecutor reservationFilterExecutor, Storage storage) {
        this.reservationFetcher = reservationFetcher;
        this.reservationExecutor = reservationExecutor;
        this.reservationFilterExecutor = reservationFilterExecutor;
        this.storage = storage;
    }

    @Async
    public void start(FilterForm filterForm) {

        do {
            reserve(filterForm);

        } while (isNotReserved());
    }

    private void reserve(FilterForm filterForm) {

        List<Reservation> reservations = reservationFetcher.fetch(filterForm);

        String  minDate = findMinDate(reservations);
        String  maxDate = findMaxDate(reservations);

        log.info(LocalDateTime.now() + ": fetch " + reservations.size() + " visits. " +
        "min date: " + minDate + ", max date: " + maxDate);

        reservations.stream()
                .filter(reservationFilterExecutor::apply)
                .findFirst()
                .ifPresent(this::reserve);
    }

    private String findMinDate(List<Reservation> reservations) {

        return reservations.stream()
                .map(Reservation::getDate)
                .min(Comparator.naturalOrder())
                .map(LocalDate::toString)
                .orElse("Not found");
    }

    private String findMaxDate(List<Reservation> reservations) {

        return reservations.stream()
                .map(Reservation::getDate)
                .max(Comparator.naturalOrder())
                .map(LocalDate::toString)
                .orElse("Not found");
    }

    private void reserve(Reservation reservation) {

        log.info(LocalDateTime.now() + ": try reserve " + reservation.getDate() +
        ", " + reservation.getHour());

        reservationExecutor.reserve(reservation);
    }

    private boolean isNotReserved() {

        return !storage.isReserved();
    }
}
