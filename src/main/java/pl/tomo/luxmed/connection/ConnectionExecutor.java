package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
class ConnectionExecutor implements ConnectionService {

    private final RequestDataBuilder requestDataBuilder;
    private final RequestFactory requestFactory;

    @Autowired
    ConnectionExecutor(RequestDataBuilder requestDataBuilder, RequestFactory requestFactory) {
        this.requestDataBuilder = requestDataBuilder;
        this.requestFactory = requestFactory;
    }

    @Override
    @SneakyThrows
    public CompletableFuture<HtmlResponse> postForHtml(ConnectionRequest request) {

        HttpContent httpContent = requestDataBuilder.createContent(request);
        HttpRequest httpRequest = requestFactory.buildRequest(request, httpContent);

        HttpResponse httpResponse = httpRequest.execute();

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        HtmlResponse htmlResponse = new HtmlResponse(httpResponse, document);

        return CompletableFuture.completedFuture(htmlResponse);
    }

    @Override
    @SneakyThrows
    public CompletableFuture<HtmlResponse> getForHtml(ConnectionRequest request) {

        HttpRequest httpRequest = requestFactory.buildRequest(request);

        HttpResponse httpResponse = httpRequest.execute();

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        HtmlResponse htmlResponse = new HtmlResponse(httpResponse, document);

        return CompletableFuture.completedFuture(htmlResponse);
    }
}
