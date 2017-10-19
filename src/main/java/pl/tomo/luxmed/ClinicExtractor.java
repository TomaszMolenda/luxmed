package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class ClinicExtractor extends Extractor {

    @Override
    String elementId() {

        return "ClinicId";
    }

    @Override
    FilterData create(String id, String name) {

        return new Clinic(id, name);
    }
}
