package pl.tomo.luxmed.reservation;

import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ReservationExtractorTest {

    @Test
    public void shouldExtractReservation() throws Exception {

        //given
        ReservationExtractor reservationExtractor = new ReservationExtractor(new DateExtractor());

        InputStream inputStream = ReservationExtractorTest.class.getClassLoader().getResourceAsStream("reservation.html");
        Document document = Jsoup.parse(inputStream, "UTF-8", "https://portalpacjenta.luxmed.pl/PatientPortal/Reservations/Reservation/Find");

        //when
        List<Reservation> reservations = reservationExtractor.extract(document);

        //then
        Assertions.assertThat(reservations).hasSize(23);
    }
}
