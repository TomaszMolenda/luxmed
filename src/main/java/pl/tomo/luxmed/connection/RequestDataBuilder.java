package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.UrlEncodedContent;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
class RequestDataBuilder {

    private final static HttpMediaType URL_ENCODED_MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded");

    HttpContent createContent(ConnectionRequest request) {

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
