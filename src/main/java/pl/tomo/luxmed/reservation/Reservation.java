package pl.tomo.luxmed.reservation;

import lombok.Data;
import pl.tomo.luxmed.reservation.filter.Filter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
class Reservation {

    private final LocalDate date;
    private final LocalTime hour;
    private final String termId;
    private final String doctor;
    private final String service;
    private final String clinic;

    boolean applyFilter(Filter filter) {

        LocalTime minTime = filter.getMinimumTime();
        LocalDate minDate = filter.getMinimumDate();

        return getHour().isAfter(minTime) &&
                getDate().isEqual(minDate);
    }
}
