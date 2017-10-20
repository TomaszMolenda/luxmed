package pl.tomo.luxmed.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicQueries extends AbstractQueries<Clinic> {

    private final Storage storage;

    @Autowired
    ClinicQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<Clinic> fetchData() {

        return storage.getClinics();
    }
}
