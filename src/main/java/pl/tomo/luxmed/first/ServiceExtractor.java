package pl.tomo.luxmed.first;

import org.springframework.stereotype.Service;
import pl.tomo.luxmed.extractor.Extractor;

@Service
class ServiceExtractor extends Extractor<MediService> {

    @Override
    public String elementId() {

        return "ServiceId";
    }

    @Override
    public MediService create(String id, String name) {

        return new MediService(id, name);
    }
}
