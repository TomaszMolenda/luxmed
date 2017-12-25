package pl.tomo.luxmed.reservation;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReservationErrorStorage {

    private List<Reservation> errorReservations = Lists.newArrayList();

    public void addErrorReservation(Reservation reservation) {

        errorReservations.add(reservation);
    }

    boolean contains(Reservation reservation) {

        return errorReservations.contains(reservation);
    }
}
