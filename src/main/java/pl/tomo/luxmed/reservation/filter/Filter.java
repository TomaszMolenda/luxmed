package pl.tomo.luxmed.reservation.filter;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@ToString
public class Filter {

    private final LocalDateTime minimumReservationTime;
    private final LocalDateTime maximumReservationTime;

    public Filter(LocalDateTime minimumReservationTime, LocalDateTime maximumReservationTime) {
        this.minimumReservationTime = minimumReservationTime;
        this.maximumReservationTime = maximumReservationTime;
    }

    public LocalTime getMinimumTime() {

        return minimumReservationTime.toLocalTime();
    }

    public LocalDate getMinimumDate() {

        return minimumReservationTime.toLocalDate();
    }

    public LocalTime getMaximumTime() {

        return maximumReservationTime.toLocalTime();
    }

    public LocalDate getMaximumDate() {

        return maximumReservationTime.toLocalDate();
    }
}
