package pl.tomo.luxmed.reservation;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateExtractorTest {

    @Test
    public void shouldExtractDate(){

        //given
        DateExtractor dateExtractor = new DateExtractor();
        LocalDate exceptedDate = LocalDate.of(2019, 11, 20);
        //when
        LocalDate extractedDate = dateExtractor.extract("Środa, 20-11-2019 Dostępnych terminów wizyt: 1, o 11:20");

        //then
        assertEquals(exceptedDate, extractedDate);
    }

}
