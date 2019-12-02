package pl.tomo.luxmed.payers;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.service.AdvancedDataFetcher;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
class PayerFetcher {

    private final AdvancedDataFetcher advancedDataFetcher;
    private final Storage storage;
    private final PayerExtractor payerExtractor;

    @Autowired
    PayerFetcher(AdvancedDataFetcher advancedDataFetcher, Storage storage, PayerExtractor payerExtractor) {
        this.advancedDataFetcher = advancedDataFetcher;
        this.storage = storage;
        this.payerExtractor = payerExtractor;
    }

    List<Payer> fetch() {

        final PayerFilterForm form = new PayerFilterForm(storage.getCityId(), storage.getServiceId(), storage.getCoordinationActivityId());
        final Document document = advancedDataFetcher.extract(form);
        final List<Payer> payers = payerExtractor.extract(document);

        storage.setPayers(payers);

        return payers;
    }
}
