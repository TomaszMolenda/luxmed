package pl.tomo.luxmed.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.reservation.filter.Filter;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationFilterExecutor {

    private final Storage storage;

    @Autowired
    ReservationFilterExecutor(Storage storage) {
        this.storage = storage;
    }

    boolean apply(Reservation reservation) {

        final Filter filter = storage.getFilter();

        storage.addLog(Log.log("Apply filter: " + filter + " to reservation: " + reservation));

        return reservation.applyFilter(filter);
    }
}
