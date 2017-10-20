package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageRefresher {

    private final Storage storage;
    private final SimpleDataFetcher simpleDataFetcher;
    private final SimpleDataExtractor simpleDataExtractor;


    @Autowired
    StorageRefresher(Storage storage, SimpleDataFetcher simpleDataFetcher, SimpleDataExtractor simpleDataExtractor) {
        this.storage = storage;
        this.simpleDataFetcher = simpleDataFetcher;
        this.simpleDataExtractor = simpleDataExtractor;
    }

    public void refreshSimpleData() {

        Document document = simpleDataFetcher.extract();

        storage.setCities(simpleDataExtractor.extractCities(document));
        storage.setClinics(simpleDataExtractor.extractClinics(document));
        storage.setServices(simpleDataExtractor.extractServices(document));
    }

    public void refreshAdvancedData(SimpleData simpleData) {

        String cityId = simpleData.getCityId();
        String clinicId = simpleData.getClinicId();
        String serviceId = simpleData.getServiceId();



    }
}
