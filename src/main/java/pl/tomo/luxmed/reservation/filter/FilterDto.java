package pl.tomo.luxmed.reservation.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class FilterDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate minimumReservationDate;
    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime minimumReservationTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate maximumReservationDate;
    @DateTimeFormat(pattern = "HH:mm")
    private final LocalTime maximumReservationTime;
}


