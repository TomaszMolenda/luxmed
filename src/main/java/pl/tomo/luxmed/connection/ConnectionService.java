package pl.tomo.luxmed.connection;

import java.util.concurrent.CompletableFuture;

public interface ConnectionService {

    CompletableFuture<HtmlResponse> postForHtml(ConnectionRequest request);

    CompletableFuture<HtmlResponse> getForHtml(ConnectionRequest request);
}
