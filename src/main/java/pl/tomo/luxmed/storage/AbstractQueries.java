package pl.tomo.luxmed.storage;

import pl.tomo.luxmed.extractor.FilterData;

import java.util.List;

public abstract class AbstractQueries<E extends FilterData> {

    public E fetch(String cityId) {

        return fetchData().stream()
                .filter(e -> e.getId().equals(cityId))
                .findAny()
                .orElse(null);
    }

    public abstract List<E> fetchData();
}
