package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SimpleDataExtractor {

    private final CityExtractor cityExtractor;
    private final ClinicExtractor clinicExtractor;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    SimpleDataExtractor(CityExtractor cityExtractor, ClinicExtractor clinicExtractor, ServiceExtractor serviceExtractor) {

        this.cityExtractor = cityExtractor;
        this.clinicExtractor = clinicExtractor;
        this.serviceExtractor = serviceExtractor;
    }

    List<City> extractCities(Document document) {

        return cityExtractor.extract(document);
    }

    List<Clinic> extractClinics(Document document) {

        return clinicExtractor.extract(document);
    }

    List<MediService> extractServices(Document document) {

        return serviceExtractor.extract(document);
    }
}
