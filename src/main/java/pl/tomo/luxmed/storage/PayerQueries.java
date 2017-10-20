package pl.tomo.luxmed.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayerQueries extends AbstractQueries<Payer> {

    private final Storage storage;

    @Autowired
    PayerQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    List<Payer> fetchData() {

        return storage.getPayers();
    }
}
