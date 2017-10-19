package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class PayerExtractor extends Extractor<Payer> {

    @Override
    String elementId() {

        return "PayerId";
    }

    @Override
    Payer create(String id, String name) {

        return new Payer(id, name);
    }
}
