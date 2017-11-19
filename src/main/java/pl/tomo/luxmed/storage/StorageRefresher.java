package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.AdvancedDataFetcher;
import pl.tomo.luxmed.service.SimpleDataFetcher;

@Service
public class StorageRefresher {

    private final Storage storage;
    private final SimpleDataFetcher simpleDataFetcher;
    private final SimpleDataExtractor simpleDataExtractor;
    private final AdvancedDataFetcher advancedDataFetcher;
    private final AdvancedDataExtractor advancedDataExtractor;


    @Autowired
    StorageRefresher(Storage storage, SimpleDataFetcher simpleDataFetcher, SimpleDataExtractor simpleDataExtractor, AdvancedDataFetcher advancedDataFetcher, AdvancedDataExtractor advancedDataExtractor) {
        this.storage = storage;
        this.simpleDataFetcher = simpleDataFetcher;
        this.simpleDataExtractor = simpleDataExtractor;
        this.advancedDataFetcher = advancedDataFetcher;
        this.advancedDataExtractor = advancedDataExtractor;
    }

    public void refreshSimpleData() {

        Document document = simpleDataFetcher.extract();

        storage.setClinics(simpleDataExtractor.extractClinics(document));
    }

    public void refreshAdvancedData(SimpleData simpleData) {

        String cityId = simpleData.getCityId();
        String clinicId = simpleData.getClinicId();
        String serviceId = simpleData.getServiceId();

        Document document = advancedDataFetcher.extract(new FilterFormWithSimpleData(cityId, clinicId, serviceId));

        storage.setDoctors(advancedDataExtractor.extractDoctors(document));
        storage.setPayers(advancedDataExtractor.extractPayers(document));
    }
}
