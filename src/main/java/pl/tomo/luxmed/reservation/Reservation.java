package pl.tomo.luxmed.reservation;

import lombok.Data;

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
}
