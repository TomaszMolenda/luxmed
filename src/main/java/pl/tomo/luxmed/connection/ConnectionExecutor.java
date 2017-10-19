package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ConnectionExecutor implements ConnectionService {

    @Autowired private RequestDataBuilder requestDataBuilder;
    @Autowired private RequestFactory requestFactory;

    @Override
    @SneakyThrows
    public HtmlResponse postForHtml(ConnectionRequest request) {

        HttpContent httpContent = requestDataBuilder.createContent(request);
        HttpRequest httpRequest = requestFactory.buildRequest(request, httpContent);

        HttpResponse httpResponse = httpRequest.execute();

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        return new HtmlResponse(httpResponse, document);
    }

    @Override
    @SneakyThrows
    public HtmlResponse getForHtml(ConnectionRequest request) {

        HttpRequest httpRequest = requestFactory.buildRequest(request);

        HttpResponse httpResponse = httpRequest.execute();

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        return new HtmlResponse(httpResponse, document);
    }
}
