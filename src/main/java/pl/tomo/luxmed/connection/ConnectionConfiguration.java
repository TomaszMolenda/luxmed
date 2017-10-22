package pl.tomo.luxmed.connection;

import com.google.api.client.http.apache.ApacheHttpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
class ConnectionConfiguration {

    @Bean
    ApacheHttpTransport apacheHttpTransport()
    {
        return new ApacheHttpTransport();
    }

    @Bean
    public TaskExecutor connectionRequestExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("luxmed-");
        executor.initialize();

        return executor;
    }
}
