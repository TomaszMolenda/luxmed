package pl.tomo.luxmed;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
class ReservationKeyExtractor {

    public String extract(Document document) {

        String html = document.toString();

        return StringUtils.substringBetween(html, "var url = '/PatientPortal/Reservations/Reservation/ReserveTerm?key=", "';");
    }
}
