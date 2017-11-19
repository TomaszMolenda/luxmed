package pl.tomo.luxmed.second;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.city.CityQueries;
import pl.tomo.luxmed.storage.ClinicQueries;
import pl.tomo.luxmed.storage.ServiceQueries;
import pl.tomo.luxmed.storage.Storage;

import java.util.HashMap;
import java.util.List;

@Service
class SecondModelGenerator {

    private final ClinicQueries clinicQueries;
    private final ServiceQueries serviceQueries;
    private final CityQueries cityQueries;
    private final Storage storage;

    @Autowired
    SecondModelGenerator(ClinicQueries clinicQueries, ServiceQueries serviceQueries, CityQueries cityQueries, Storage storage) {
        this.clinicQueries = clinicQueries;
        this.serviceQueries = serviceQueries;
        this.cityQueries = cityQueries;
        this.storage = storage;
    }

    HashMap<String, ?> generate(FilterInformation filterInformation) {

        HashMap<String, Object> model = Maps.newHashMap();

        model.put("clinic", clinicQueries.fetch(filterInformation.getCityId()));
        model.put("service", serviceQueries.fetch(filterInformation.getServiceId()));
        model.put("city", cityQueries.fetch(filterInformation.getCityId()));

        return model;
    }

    HashMap<String, ?> generate() {

        HashMap<String, List> model = Maps.newHashMap();

        model.put("doctors", storage.getDoctors());
        model.put("payers", storage.getPayers());

        return model;
    }


}
