package pl.tomo.luxmed.reservation.filter;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ToString
@RequiredArgsConstructor
public class Filter {

    private final ReservationDate minDate;
    private final ReservationDate maxDate;

    public static Filter empty() {

        final ReservationDate minReservationDate = ReservationDate.reservationDate(LocalDate.now(), LocalTime.now());
        final ReservationDate maxReservationDate = ReservationDate.reservationDate(LocalDate.now().plusDays(14), LocalTime.now());

        return new Filter(minReservationDate, maxReservationDate);
    }

    public LocalDateTime getMinDateTime() {

        return LocalDateTime.of(getMinDate(), getMinTime());
    }

    public LocalTime getMinTime() {

        return minDate.getTime();
    }

    public LocalDate getMinDate() {

        return minDate.getDate();
    }

    public LocalTime getMaxTime() {

        return maxDate.getTime();
    }

    public LocalDate getMaxDate() {

        return maxDate.getDate();
    }
}
