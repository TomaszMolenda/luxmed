package pl.tomo.luxmed.main;

import com.google.common.collect.Maps;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
class ModelGenerator {

    private final MainViewModelCreator mainViewModelCreator;
    private final ClinicExtractor clinicExtractor;
    private final CityExtractor cityExtractor;
    private final ServiceExtractor serviceExtractor;

    @Autowired
    ModelGenerator(MainViewModelCreator mainViewModelCreator, ClinicExtractor clinicExtractor, CityExtractor cityExtractor, ServiceExtractor serviceExtractor) {
        this.mainViewModelCreator = mainViewModelCreator;
        this.clinicExtractor = clinicExtractor;
        this.cityExtractor = cityExtractor;
        this.serviceExtractor = serviceExtractor;
    }

    HashMap<String, ?> generate() {

        Document document = mainViewModelCreator.extract();

        HashMap<String, List> model = Maps.newHashMap();

        model.put("clinics", clinicExtractor.extract(document));
        model.put("services", serviceExtractor.extract(document));
        model.put("cities", cityExtractor.extract(document));

        return model;
    }
}
