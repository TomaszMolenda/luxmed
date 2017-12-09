package pl.tomo.luxmed.payers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.AbstractQueries;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Service
public class PayerQueries extends AbstractQueries<Payer> {

    private final Storage storage;

    @Autowired
    PayerQueries(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<Payer> fetchData() {

        return storage.getPayers();
    }
}
