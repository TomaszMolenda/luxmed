package pl.tomo.luxmed.reservation.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FilterForm {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate minDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime minTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate maxDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime maxTime;
}
