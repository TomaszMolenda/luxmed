package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class DoctorExtractor extends Extractor<Doctor> {

    @Override
    String elementId() {

        return "DoctorId";
    }

    @Override
    Doctor create(String id, String name) {

        return new Doctor(id, name);
    }
}
