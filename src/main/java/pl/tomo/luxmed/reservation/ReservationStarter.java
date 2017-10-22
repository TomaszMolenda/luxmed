package pl.tomo.luxmed.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.FilterForm;

import java.util.List;

@Service
public class ReservationStarter {

    private final ReservationFetcher reservationFetcher;

    @Autowired
    ReservationStarter(ReservationFetcher reservationFetcher) {
        this.reservationFetcher = reservationFetcher;
    }

    public void start(FilterForm filterForm) {

        List<Reservation> reservations = reservationFetcher.fetch(filterForm);

        reservations.stream()
                .forEach(reservation -> System.out.println(reservation));
    }
}
