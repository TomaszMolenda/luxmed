package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
class RequestVerificationTokenExtractor {

    public String extract(Document document) {

        return document.select("input[name=__RequestVerificationToken]").first().attr("value");
    }
}
