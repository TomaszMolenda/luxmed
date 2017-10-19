package pl.tomo.luxmed.connection;

import com.google.api.client.http.*;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
class ConnectionExecutor implements ConnectionService {

    private final static HttpMediaType URL_ENCODED_MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded");

    @Autowired private RequestDataBuilder requestDataBuilder;
    @Autowired private RequestFactory requestFactory;

    @Override
    @SneakyThrows
    public HtmlResponse postForHtml(ConnectionRequest request) {

        HttpContent httpContent = asHttpContent(request);

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

    private HttpContent asHttpContent(ConnectionRequest request) {

        UrlEncodedContent urlEncodedContent = new UrlEncodedContent(dataAsMap(request));
        urlEncodedContent.setMediaType(URL_ENCODED_MEDIA_TYPE);

        return urlEncodedContent;
    }

    private Map<String, String> dataAsMap(ConnectionRequest request) {
        return request
                .getData()
                .stream()
                .collect(Collectors.toMap(DataEntry::getKey, DataEntry::getValue));

    }
}
