package pl.tomo.luxmed.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.tomo.luxmed.reservation.filter.Filter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(exclude = "termId")
class Reservation {

    private final LocalDate date;
    private final LocalTime hour;
    private final String termId;
    private final String doctor;
    private final String service;
    private final String clinic;

    boolean applyFilter(Filter filter) {

        if (filter.isInvalid()) {

            return false;
        }

        if (filter.hasOnlyDate()) {

            return applyOnlyDate(filter);
        }

        if (filter.hasOnlyTime()) {

            return applyOnlyTime(filter);
        }

        if (filter.hasDateAndTime()) {

            return applyDateAndTime(filter);
        }

        return false;
    }

    private boolean applyOnlyDate(Filter filter) {

        final LocalDate minReservationDate = filter.getMinDate();
        final LocalDate maxReservationDate = filter.getMaxDate();

        final LocalDate reservationDate = getDate();

        if (reservationDate.isEqual(minReservationDate) || reservationDate.isEqual(maxReservationDate)) {
            return true;
        }

        return reservationDate.isAfter(minReservationDate) &&
                reservationDate.isBefore(maxReservationDate);
    }

    private boolean applyOnlyTime(Filter filter) {

        final LocalTime minReservationTime = filter.getMinTime();
        final LocalTime maxReservationTime = filter.getMaxTime();

        final LocalTime reservationTime = getHour();

        return reservationTime.isAfter(minReservationTime) &&
                reservationTime.isBefore(maxReservationTime);
    }

    private boolean applyDateAndTime(Filter filter) {

        final LocalDateTime minReservationTime = LocalDateTime.of(filter.getMinDate(), filter.getMinTime());
        final LocalDateTime maxReservationTime = LocalDateTime.of(filter.getMaxDate(), filter.getMaxTime());

        final LocalDateTime reservationTime = LocalDateTime.of(getDate(), getHour());

        return reservationTime.isAfter(minReservationTime) &&
                reservationTime.isBefore(maxReservationTime);
    }
}
