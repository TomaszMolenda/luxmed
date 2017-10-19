package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
class FilterDataExtractor {

    private final SecondStepExtractor secondStepExtractor;

    @Autowired
    FilterDataExtractor(SecondStepExtractor secondStepExtractor) {
        this.secondStepExtractor = secondStepExtractor;
    }

    SecondStepFilter extractSecondStep(List<Cookie> authorizationCookies, FilterForm filterForm) {

        return secondStepExtractor.extract(authorizationCookies, filterForm);
    }




}
