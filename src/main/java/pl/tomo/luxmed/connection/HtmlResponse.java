package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpResponse;
import lombok.Builder;
import lombok.Data;
import org.jsoup.nodes.Document;

@Builder
@Data
public class HtmlResponse
{
    final HttpResponse httpResponse;
    final Document document;
}
