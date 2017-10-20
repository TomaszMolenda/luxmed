package pl.tomo.luxmed.first;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

import java.util.HashMap;
import java.util.List;

@Service
class FirstModelGenerator {

    private final Storage storage;

    @Autowired
    FirstModelGenerator(Storage storage) {
        this.storage = storage;
    }


    HashMap<String, ?> generate() {

        HashMap<String, List> model = Maps.newHashMap();

        model.put("clinics", storage.getClinics());
        model.put("services", storage.getServices());
        model.put("cities", storage.getCities());

        return model;
    }
}
