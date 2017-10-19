package pl.tomo.luxmed;

import com.google.api.client.http.apache.ApacheHttpTransport;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    ApacheHttpTransport apacheHttpTransport()
    {
        return new ApacheHttpTransport();
    }
}
