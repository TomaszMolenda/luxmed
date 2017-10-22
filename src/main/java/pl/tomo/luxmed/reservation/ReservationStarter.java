package pl.tomo.luxmed.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservationStarter {

    private boolean isNotReserved = true;

    private final ReservationFetcher reservationFetcher;
    private final ReservationExecutor reservationExecutor;


    @Autowired
    ReservationStarter(ReservationFetcher reservationFetcher, ReservationExecutor reservationExecutor) {
        this.reservationFetcher = reservationFetcher;
        this.reservationExecutor = reservationExecutor;
    }

    public void start(FilterForm filterForm) {

        do {
            List<Reservation> reservations = reservationFetcher.fetch(filterForm);

            String  minDate = findMinDate(reservations);
            String  maxDate = findMaxDate(reservations);

            System.out.println(LocalDateTime.now() + ": fetch " + reservations.size() + " visits.\n" +
            "min date: " + minDate + ", max date: " + maxDate);

            reservations.stream()
                    .filter(this::hasValidDateAndTime)
                    .findFirst()
                    .ifPresent(this::reserve);


        } while (isNotReserved);
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

    private boolean hasValidDateAndTime(Reservation reservation) {

        return reservation.getHour().isAfter(LocalTime.of(14,55)) &&
                reservation.getDate().isEqual(LocalDate.now().plusDays(1));
    }

    private void reserve(Reservation reservation) {

        System.out.println(LocalDateTime.now() + ": try reserve " + reservation.getDate() +
        ", " + reservation.getHour());

        boolean reserve = reservationExecutor.reserve(reservation);

        isNotReserved = !reserve;
    }
}
