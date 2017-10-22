package pl.tomo.luxmed.reservation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
class ReservationFilterExecutor {

    boolean apply(Reservation reservation) {

        return reservation.getHour().isAfter(LocalTime.of(14,55)) &&
                reservation.getDate().isEqual(LocalDate.now().plusDays(1));
    }
}
