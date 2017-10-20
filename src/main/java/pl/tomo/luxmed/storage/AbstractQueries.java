package pl.tomo.luxmed.storage;

import pl.tomo.luxmed.extractor.FilterData;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

abstract class AbstractQueries<E extends FilterData> {

    public String fetch(String cityId) {

        return fetchData().stream()
                .filter(e -> e.getId().equals(cityId))
                .map(E::getName)
                .findAny()
                .orElse(EMPTY);
    }

    abstract List<E> fetchData();
}
