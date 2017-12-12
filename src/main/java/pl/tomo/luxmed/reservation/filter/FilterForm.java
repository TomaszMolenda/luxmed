package pl.tomo.luxmed.reservation.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FilterForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minimumReservationDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime minimumReservationTime;

    FilterForm() {
    }

    FilterForm(LocalDate minimumReservationDate, LocalTime minimumReservationTime) {
        this.minimumReservationDate = minimumReservationDate;
        this.minimumReservationTime = minimumReservationTime;
    }
}
