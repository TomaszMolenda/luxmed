package pl.tomo.luxmed.reservation;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import pl.tomo.luxmed.reservation.filter.Filter;
import pl.tomo.luxmed.reservation.filter.ReservationDate;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.tomo.luxmed.reservation.filter.ReservationDate.reservationDate;

public class ReservationTest {

    @Test
    public void shouldApplyFilter() {

    	// given
        LocalDate now = now();
        Reservation reservation = new Reservation(now, LocalTime.of(10, 10), "termId", "doctor", "service", "clinic");
        Filter filter = new Filter(reservationDate(now, null), reservationDate(now.plusDays(1), null));

        // when
        boolean applyFilter = reservation.applyFilter(filter);

        // then
        assertThat(applyFilter).isTrue();
    }

}
