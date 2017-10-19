package pl.tomo.luxmed;

import org.springframework.stereotype.Service;
import pl.tomo.luxmed.extractor.Extractor;

@Service
class DoctorExtractor extends Extractor<Doctor> {

    @Override
    public String elementId() {

        return "DoctorId";
    }

    @Override
    public Doctor create(String id, String name) {

        return new Doctor(id, name);
    }
}
