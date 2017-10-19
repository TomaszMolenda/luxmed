package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CitiesExtractor {

    List<City> extract(Document document) {

        Element element = document.getElementById("CityId");

        return element.childNodes().stream()
                .filter(this::hasAttribute)
                .map(this::createCity)
                .collect(Collectors.toList());
    }

    private boolean hasAttribute(Node node) {

        return node.hasAttr("data-favourite");
    }

    private City createCity(Node node) {

        String id = node.attr("value");
        String name = ((Element) node).text();

        return new City(id, name);
    }
}
