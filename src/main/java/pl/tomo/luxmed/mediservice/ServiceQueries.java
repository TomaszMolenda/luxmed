package pl.tomo.luxmed.mediservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.city.City;
import pl.tomo.luxmed.extractor.FilterData;
import pl.tomo.luxmed.storage.AbstractQueries;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class ServiceQueries extends AbstractQueries<MediService> {

    private final Storage storage;

    @Autowired
    ServiceQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<MediService> fetchData() {

        return storage.getServices();
    }
}
