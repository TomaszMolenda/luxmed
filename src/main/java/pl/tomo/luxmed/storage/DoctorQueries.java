package pl.tomo.luxmed.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorQueries extends AbstractQueries<Doctor> {

    private final Storage storage;

    @Autowired
    DoctorQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    List<Doctor> fetchData() {

        return storage.getDoctors();
    }
}
