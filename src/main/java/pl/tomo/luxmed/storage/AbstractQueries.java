package pl.tomo.luxmed.storage;

import pl.tomo.luxmed.extractor.FilterData;

import java.util.List;

abstract class AbstractQueries<E extends FilterData> {

    public E fetch(String cityId) {

        return fetchData().stream()
                .filter(e -> e.getId().equals(cityId))
                .findAny()
                .orElse(null);
    }

    abstract List<E> fetchData();
}
