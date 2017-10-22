package pl.tomo.luxmed.reservation;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class ReservationExtractor {

    List<Reservation> extract(Document document) {

        return document.getElementsByClass("tableList")
                .first()
                .getElementsByClass("content")
                .stream()
                .flatMap(this::create)
                .collect(Collectors.toList());
    }

    private Stream<Reservation> create(Element element) {

        String date = element.parent().getElementsByClass("title").text();

        return element.getElementsByClass("reserveTable")
                .first()
                .getElementsByTag("tbody")
                .first()
                .getElementsByTag("tr")
                .stream()
                .map(this::obtainReservationInfo)
                .map(e -> create(e, date));
    }

    private Element obtainReservationInfo(Element element) {

        return element.getElementsByTag("td").first();
    }

    private Reservation create(Element element, String date) {

        final String hour = element.attr("data-sort");
        final String termId = element.getElementsByClass("reserveButtonDiv").first().attr("term-id");

        final Elements otherReservationInformation = obtainElementsWithOtherReservationInformation(element);

        final String doctor = otherReservationInformation.get(0).text();
        final String service = otherReservationInformation.get(1).text();
        final String clinic = otherReservationInformation.get(2).text();

        return new Reservation(convertDate(date), convertHour(hour), termId, doctor, service, clinic);
    }

    private Elements obtainElementsWithOtherReservationInformation(Element element) {

        return element.siblingElements().first().getElementsByAttribute("data-sort");
    }

    private LocalTime convertHour(String hour) {

        return LocalTime.parse(hour, DateTimeFormatter.ofPattern("H:mm"));
    }

    private LocalDate convertDate(String date) {

        return LocalDate.parse(StringUtils.substringAfter(date, ", "),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
