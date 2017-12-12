package pl.tomo.luxmed.reservation.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Filter {

    private final LocalDateTime minimumReservationTime;

    public Filter(LocalDateTime minimumReservationTime) {
        this.minimumReservationTime = minimumReservationTime;
    }

    public LocalTime getMinimumTime() {

        return minimumReservationTime.toLocalTime();
    }

    public LocalDate getMinimumDate() {

        return minimumReservationTime.toLocalDate();
    }
}
