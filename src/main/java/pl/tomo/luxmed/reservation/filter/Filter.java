package pl.tomo.luxmed.reservation.filter;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
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

    public boolean isInvalid() {

        if (minDate == null || maxDate == null) {

            return true;
        }

        if (minDate.hasOnlyTime() && maxDate.hasDateAndTime()) {

            return true;
        }

        if (maxDate.hasOnlyTime() && minDate.hasDateAndTime()) {

            return true;
        }

        return false;

    }

    public boolean hasOnlyTime() {

        return minDate.hasOnlyTime() && maxDate.hasOnlyTime();
    }

    public boolean hasDateAndTime() {

        return minDate.hasDateAndTime() && maxDate.hasDateAndTime();


    }
}
