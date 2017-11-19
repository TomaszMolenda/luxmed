package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SimpleDataExtractor {

    private final ClinicExtractor clinicExtractor;

    @Autowired
    SimpleDataExtractor(ClinicExtractor clinicExtractor) {

        this.clinicExtractor = clinicExtractor;
    }

    List<Clinic> extractClinics(Document document) {

        return clinicExtractor.extract(document);
    }
}
