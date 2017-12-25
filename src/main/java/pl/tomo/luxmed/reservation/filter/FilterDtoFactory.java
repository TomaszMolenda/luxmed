package pl.tomo.luxmed.reservation.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

@Service
class FilterDtoFactory {

    private final Storage storage;

    @Autowired
    FilterDtoFactory(Storage storage) {
        this.storage = storage;
    }

    FilterDto create() {

        final Filter filter = storage.getFilter();

        if (filter == null) {

            return new FilterDto(null, null, null, null);

        } else {

            return new FilterDto(filter.getMinDate(), filter.getMinTime(),
                    filter.getMaxDate(), filter.getMaxTime());
        }
    }
}
