package pl.tomo.luxmed.connection;

public interface ConnectionService {

    HtmlResponse postForHtml(ConnectionRequest request);

    HtmlResponse getForHtml(ConnectionRequest request);
}
