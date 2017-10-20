package pl.tomo.luxmed.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceQueries extends AbstractQueries<MediService> {

    private final Storage storage;

    @Autowired
    ServiceQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    List<MediService> fetchData() {

        return storage.getServices();
    }
}
