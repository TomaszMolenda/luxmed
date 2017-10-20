package pl.tomo.luxmed.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityQueries extends AbstractQueries<City> {

    private final Storage storage;

    @Autowired
    CityQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<City> fetchData() {

        return storage.getCities();
    }

}
