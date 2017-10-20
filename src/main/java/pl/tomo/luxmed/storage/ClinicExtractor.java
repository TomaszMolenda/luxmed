package pl.tomo.luxmed.storage;

import org.springframework.stereotype.Service;
import pl.tomo.luxmed.extractor.Extractor;

@Service
class ClinicExtractor extends Extractor<Clinic> {

    @Override
    public String elementId() {

        return "ClinicId";
    }

    @Override
    public Clinic create(String id, String name) {

        return new Clinic(id, name);
    }
}
