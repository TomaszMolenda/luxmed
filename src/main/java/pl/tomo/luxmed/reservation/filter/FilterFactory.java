package pl.tomo.luxmed.reservation.filter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

import static pl.tomo.luxmed.reservation.filter.ReservationDate.reservationDate;

@Component
class FilterFactory {

    Filter create(FilterForm filterForm) {

        final LocalDate minDate = filterForm.getMinDate();
        final LocalTime minTime = filterForm.getMinTime();

        ReservationDate minReservationDate = reservationDate(minDate, minTime);

        final LocalDate maxDate = filterForm.getMaxDate();
        final LocalTime maxTime = filterForm.getMaxTime();

        ReservationDate maxReservationDate = reservationDate(maxDate, maxTime);

        return new Filter(minReservationDate, maxReservationDate);
    }
}
