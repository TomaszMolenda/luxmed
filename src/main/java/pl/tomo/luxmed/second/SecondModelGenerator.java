package pl.tomo.luxmed.second;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.CityQueries;
import pl.tomo.luxmed.storage.ClinicQueries;
import pl.tomo.luxmed.storage.ServiceQueries;

import java.util.HashMap;

@Service
class SecondModelGenerator {

    private final ClinicQueries clinicQueries;
    private final ServiceQueries serviceQueries;
    private final CityQueries cityQueries;

    @Autowired
    SecondModelGenerator(ClinicQueries clinicQueries, ServiceQueries serviceQueries, CityQueries cityQueries) {
        this.clinicQueries = clinicQueries;
        this.serviceQueries = serviceQueries;
        this.cityQueries = cityQueries;
    }

    HashMap<String, ?> generate(FilterInformation filterInformation) {

        HashMap<String, String> model = Maps.newHashMap();


        model.put("clinic", clinicQueries.fetch(filterInformation.getCityId()));
        model.put("service", serviceQueries.fetch(filterInformation.getServiceId()));
        model.put("city", cityQueries.fetch(filterInformation.getCityId()));

        return model;
    }
}
