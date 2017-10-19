package pl.tomo.luxmed.connection;

import lombok.*;
import org.springframework.http.HttpMethod;

import java.util.List;

@Builder
@Data
public class ConnectionRequest
{
    String url;
    @Singular("data")
    List<DataEntry> data;
    @Singular("cookie")
    List<Cookie> cookie;
    HttpMethod httpMethod;
}
