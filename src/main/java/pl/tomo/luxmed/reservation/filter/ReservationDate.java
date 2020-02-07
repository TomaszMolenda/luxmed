package pl.tomo.luxmed.reservation.filter;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDate {

    private final LocalDate date;
    private final LocalTime time;

    private ReservationDate(LocalDate date, LocalTime time) {

        this.date = date;
        this.time = time;
    }

    public static ReservationDate reservationDate(LocalDate date, LocalTime time) {
        return new ReservationDate(date, time);
    }

    LocalDate getDate() {
        return date;
    }

    LocalTime getTime() {
        return time;
    }

    boolean hasOnlyTime() {

        return date == null && time != null;
    }

    boolean hasOnlyDate() {

        return date != null && time == null;
    }

    boolean hasDateAndTime() {

        return date != null && time != null;
    }
}
