package pl.tomo.luxmed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.Cookie;

import java.util.List;

@Service
class FilterDataExtractor {

    private final FirstStepExtractor firstStepExtractor;
    private final SecondStepExtractor secondStepExtractor;

    @Autowired
    FilterDataExtractor(FirstStepExtractor firstStepExtractor, SecondStepExtractor secondStepExtractor) {
        this.firstStepExtractor = firstStepExtractor;
        this.secondStepExtractor = secondStepExtractor;
    }

    FirstStepFilter extractFirstStep(List<Cookie> authorizationCookies) {

        return firstStepExtractor.extract(authorizationCookies);
    }

    SecondStepFilter extractSecondStep(List<Cookie> authorizationCookies, FilterForm filterForm) {

        return secondStepExtractor.extract(authorizationCookies, filterForm);
    }




}
