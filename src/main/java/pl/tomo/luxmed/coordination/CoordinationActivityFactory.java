package pl.tomo.luxmed.coordination;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
class CoordinationActivityFactory {

    CoordinationActivity create(Element element) {

        final String name = String.format("%s - %s",
                element.attr("datacategory"),
                element.attr("datasubcategory"));

        final String url = element.attr("href");

        return new CoordinationActivity(name, url);
    }
}
