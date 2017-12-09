package pl.tomo.luxmed.coordination;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.storage.Storage;

@Service
class RequestVerificationTokenSaver {

    private final Storage storage;

    @Autowired
    RequestVerificationTokenSaver(Storage storage) {
        this.storage = storage;
    }

    void save(Document document) {

        storage.setRequestVerificationToken(
                document
                    .select("input[name=__RequestVerificationToken]")
                    .first()
                    .attr("value"));
    }
}
