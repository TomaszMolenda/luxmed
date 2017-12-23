package pl.tomo.luxmed.reservation;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Log;
import pl.tomo.luxmed.storage.Storage;

@Service
class ReservationStatusUpdater {

    private final Storage storage;

    @Autowired
    ReservationStatusUpdater(Storage storage) {
        this.storage = storage;
    }

    void update(Document document) {

        storage.addLog(Log.log("Reservation ok"));

        storage.setReserved(checkStatus(document));
    }

    private boolean checkStatus(Document document) {

        return document.getElementsByClass("header").text().equals("Zarezerwowano wizytę.");
    }
}
