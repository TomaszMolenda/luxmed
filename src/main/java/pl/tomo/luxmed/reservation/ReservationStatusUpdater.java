package pl.tomo.luxmed.reservation;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationStatusUpdater {

    private final Storage storage;
    private final ReservationErrorStorage reservationErrorStorage;

    @Autowired
    ReservationStatusUpdater(Storage storage, ReservationErrorStorage reservationErrorStorage) {
        this.storage = storage;
        this.reservationErrorStorage = reservationErrorStorage;
    }

    void update(Reservation reservation, Document document) {

        if (hasError(document)) {

            reservationErrorStorage.addErrorReservation(reservation);
        }

        storage.addLog(Log.log("Reservation ok"));

        storage.setReserved(checkStatus(document));
    }

    private boolean hasError(Document document) {

        return document.getElementsByClass("error-page").toString().contains("LUX MED jest obecnie niedostępny");
    }

    private boolean checkStatus(Document document) {

        return document.getElementsByClass("header").text().equals("Zarezerwowano wizytę.");
    }
}
