package pl.tomo.luxmed.reservation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
class DateExtractor {

    private static DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static Pattern DATE_PATTERN = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");

    LocalDate extract(String expression) {

        Matcher matcher = DATE_PATTERN.matcher(expression);

        if (matcher.find()) {
            String date = matcher.group();
            return LocalDate.parse(date, FOMATTER);
        } else {
            throw new IllegalStateException("unparsable date");
        }
    }
}
