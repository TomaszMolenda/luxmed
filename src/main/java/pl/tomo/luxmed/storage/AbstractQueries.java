package pl.tomo.luxmed.storage;

import pl.tomo.luxmed.extractor.FilterData;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public abstract class AbstractQueries<E extends FilterData> {

    public E fetch(String cityId) {

        return fetchData().stream()
                .filter(e -> e.getId().equals(cityId))
                .findAny()
                .orElse(null);
    }

    public String getData(String dataId) {

        return fetchData().stream()
                .filter(data -> data.getId().equalsIgnoreCase(dataId))
                .findAny()
                .map(FilterData::getName)
                .orElse(EMPTY);
    }

    public abstract List<E> fetchData();
}
