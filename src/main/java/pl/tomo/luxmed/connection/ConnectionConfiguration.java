package pl.tomo.luxmed.connection;

import com.google.api.client.http.apache.ApacheHttpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConnectionConfiguration {

    @Bean
    ApacheHttpTransport apacheHttpTransport()
    {
        return new ApacheHttpTransport();
    }
}
