package pl.tomo.luxmed.reservation.filter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
class FilterFactory {

    Filter create(FilterForm filterForm) {

        final LocalDate minimumReservationDate = filterForm.getMinimumReservationDate();
        final LocalTime minimumReservationTime = filterForm.getMinimumReservationTime();

        final LocalDateTime minimumReservationDateTime = LocalDateTime.of(minimumReservationDate, minimumReservationTime);

        return new Filter(minimumReservationDateTime);
    }
}
