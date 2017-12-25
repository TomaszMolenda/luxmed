package pl.tomo.luxmed.reservation.filter;

import java.time.LocalDate;
import java.time.LocalTime;

class ReservationDate {

    private final LocalDate date;
    private final LocalTime time;

    private ReservationDate(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    static ReservationDate reservationDate(LocalDate date, LocalTime time) {
        return new ReservationDate(date, time);
    }

    LocalDate getDate() {
        return date;
    }

    LocalTime getTime() {
        return time;
    }
}
