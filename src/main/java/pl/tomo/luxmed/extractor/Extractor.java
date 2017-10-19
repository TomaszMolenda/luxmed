package pl.tomo.luxmed.extractor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Extractor<T extends FilterData> {

    public List<T> extract(Document document) {

        Element element = document.getElementById(elementId());

        return element.childNodes().stream()
                .filter(this::hasAttribute)
                .map(this::create)
                .collect(Collectors.toList());
    }

    private boolean hasAttribute(Node node) {

        return node.hasAttr("data-favourite");
    }

    private T create(Node node) {

        String id = node.attr("value");
        String name = ((Element) node).text();

        return create(id, name);
    }

    public abstract String elementId();
    public abstract T create(String id, String name);
}