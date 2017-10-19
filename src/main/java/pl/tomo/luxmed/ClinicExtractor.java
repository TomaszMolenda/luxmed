package pl.tomo.luxmed;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ClinicExtractor {

    List<Clinic> extract(Document document) {

        Element element = document.getElementById("ClinicId");

        return element.childNodes().stream()
                .filter(this::hasAttribute)
                .map(this::createClinic)
                .collect(Collectors.toList());
    }

    private boolean hasAttribute(Node node) {

        return node.hasAttr("data-favourite");
    }

    private Clinic createClinic(Node node) {

        String id = node.attr("value");
        String name = ((Element) node).text();

        return new Clinic(id, name);
    }
}
