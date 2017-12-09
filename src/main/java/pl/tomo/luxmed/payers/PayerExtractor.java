package pl.tomo.luxmed.payers;

import org.springframework.stereotype.Service;
import pl.tomo.luxmed.extractor.Extractor;

@Service
class PayerExtractor extends Extractor<Payer> {

    @Override
    public String elementId() {

        return "PayerId";
    }

    @Override
    public Payer create(String id, String name) {

        return new Payer(id, name);
    }
}
