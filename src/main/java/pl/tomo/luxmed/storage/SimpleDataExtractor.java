package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SimpleDataExtractor {

    private final ClinicExtractor clinicExtractor;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    SimpleDataExtractor(ClinicExtractor clinicExtractor, ServiceExtractor serviceExtractor) {

        this.clinicExtractor = clinicExtractor;
        this.serviceExtractor = serviceExtractor;
    }

    List<Clinic> extractClinics(Document document) {

        return clinicExtractor.extract(document);
    }

    List<MediService> extractServices(Document document) {

        return serviceExtractor.extract(document);
    }
}
