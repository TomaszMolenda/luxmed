package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class ClinicExtractor extends Extractor<Clinic> {

    @Override
    String elementId() {

        return "ClinicId";
    }

    @Override
    Clinic create(String id, String name) {

        return new Clinic(id, name);
    }
}
