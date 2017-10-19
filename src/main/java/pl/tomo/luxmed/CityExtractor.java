package pl.tomo.luxmed;

import org.springframework.stereotype.Service;

@Service
class CityExtractor extends Extractor <City> {

    @Override
    String elementId() {

        return "CityId";
    }

    @Override
    City create(String id, String name) {

        return new City(id, name);
    }
}
