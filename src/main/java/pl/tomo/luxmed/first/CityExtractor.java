package pl.tomo.luxmed.first;

import org.springframework.stereotype.Service;
import pl.tomo.luxmed.extractor.Extractor;

@Service
class CityExtractor extends Extractor<City> {

    @Override
    public String elementId() {

        return "CityId";
    }

    @Override
    public City create(String id, String name) {

        return new City(id, name);
    }
}
