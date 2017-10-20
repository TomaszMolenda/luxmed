package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageRefresher {

    private final Storage storage;
    private final SimpleDataFetcher simpleDataFetcher;
    private final CityExtractor cityExtractor;
    private final ClinicExtractor clinicExtractor;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    StorageRefresher(Storage storage, SimpleDataFetcher simpleDataFetcher, CityExtractor cityExtractor, ClinicExtractor clinicExtractor, ServiceExtractor serviceExtractor) {
        this.storage = storage;
        this.simpleDataFetcher = simpleDataFetcher;
        this.cityExtractor = cityExtractor;
        this.clinicExtractor = clinicExtractor;
        this.serviceExtractor = serviceExtractor;
    }

    public void refresh() {

        Document document = simpleDataFetcher.extract();

        storage.setCities(cityExtractor.extract(document));
        storage.setClinics(clinicExtractor.extract(document));
        storage.setServices(serviceExtractor.extract(document));
    }
}
