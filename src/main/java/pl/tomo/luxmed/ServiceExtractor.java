package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class ServiceExtractor extends Extractor<MediService> {

    @Override
    String elementId() {

        return "ServiceId";
    }

    @Override
    MediService create(String id, String name) {

        return new MediService(id, name);
    }
}
