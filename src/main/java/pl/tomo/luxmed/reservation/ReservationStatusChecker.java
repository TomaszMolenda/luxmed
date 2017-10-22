package pl.tomo.luxmed.reservation;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
class ReservationStatusChecker {

    boolean checkSuccess(Document document) {

        return document.getElementsByClass("header").text().equals("Zarezerwowano wizytę.");
    }
}
