package pl.tomo.luxmed;

import com.google.api.client.http.HttpResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;


public class ConnectionResponse implements Serializable
{
    HttpResponse httpResponse;
    String referrer;
    Map<String, String> cookies;
}
