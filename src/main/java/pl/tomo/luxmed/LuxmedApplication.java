package pl.tomo.luxmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LuxmedApplication {

	public static final String URL = "https://portalpacjenta.luxmed.pl";

	public static void main(String[] args) {
		SpringApplication.run(LuxmedApplication.class, args);
	}
}
