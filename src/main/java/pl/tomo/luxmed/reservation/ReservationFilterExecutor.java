package pl.tomo.luxmed.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.reservation.filter.Filter;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationFilterExecutor {

    private final Storage storage;
    private final ReservationErrorStorage reservationErrorStorage;

    @Autowired
    ReservationFilterExecutor(Storage storage, ReservationErrorStorage reservationErrorStorage) {
        this.storage = storage;
        this.reservationErrorStorage = reservationErrorStorage;
    }

    boolean apply(Reservation reservation) {

        if (reservationErrorStorage.contains(reservation)) {

            return false;
        }

        final Filter filter = storage.getFilter();

        final boolean applyFilter = reservation.applyFilter(filter);

        if (applyFilter) {

            storage.addLog(Log.log("Apply filter: " + filter + " to reservation: " + reservation));
        }

        return applyFilter;


    }
}
