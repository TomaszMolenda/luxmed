package pl.tomo.luxmed.connection;

import java.util.concurrent.CompletableFuture;

public interface ConnectionService {

    HtmlResponse postForHtml(ConnectionRequest request);

    HtmlResponse getForHtml(ConnectionRequest request);
}
