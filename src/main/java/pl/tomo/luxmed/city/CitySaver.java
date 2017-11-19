package pl.tomo.luxmed.city;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
public class CitySaver {

    private final CityExtractor cityExtractor;
    private final Storage storage;

    @Autowired
    CitySaver(CityExtractor cityExtractor, Storage storage) {
        this.cityExtractor = cityExtractor;
        this.storage = storage;
    }

    public void save(Document document) {

        final List<City> cities = cityExtractor.extract(document);

        storage.setCities(cities);
    }
}
