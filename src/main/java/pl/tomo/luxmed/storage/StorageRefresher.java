package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.AdvancedDataFetcher;

@Service
public class StorageRefresher {

    private final Storage storage;
    private final AdvancedDataFetcher advancedDataFetcher;
    private final AdvancedDataExtractor advancedDataExtractor;


    @Autowired
    StorageRefresher(Storage storage, AdvancedDataFetcher advancedDataFetcher, AdvancedDataExtractor advancedDataExtractor) {
        this.storage = storage;
        this.advancedDataFetcher = advancedDataFetcher;
        this.advancedDataExtractor = advancedDataExtractor;
    }

    public void refreshAdvancedData(SimpleData simpleData) {

        String cityId = simpleData.getCityId();
        String clinicId = simpleData.getClinicId();
        String serviceId = simpleData.getServiceId();

        Document document = advancedDataFetcher.extract(new FilterFormWithSimpleData(cityId, clinicId, serviceId));

        storage.setDoctors(advancedDataExtractor.extractDoctors(document));
    }
}
