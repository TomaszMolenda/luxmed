package pl.tomo.luxmed.mediservice;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.AdvancedDataFetcher;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
class MediServiceFetcher {

    private final AdvancedDataFetcher advancedDataFetcher;
    private final Storage storage;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    MediServiceFetcher(AdvancedDataFetcher advancedDataFetcher, Storage storage, ServiceExtractor serviceExtractor) {
        this.advancedDataFetcher = advancedDataFetcher;
        this.storage = storage;
        this.serviceExtractor = serviceExtractor;
    }

    List<MediService> fetch() {

        String coordinationActivityId = storage.getCoordinationActivityId();

        final MediServiceFilterForm form = new MediServiceFilterForm(storage.getCityId(), coordinationActivityId);
        final Document document = advancedDataFetcher.extract(form);
        final List<MediService> services = serviceExtractor.extract(document);

        storage.setServices(services);

        return services;
    }
}
