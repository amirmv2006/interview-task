package ir.amv.snippets.tbatask;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@SpringBootApplication
public class TbaTaskApplication {

	public static final String IN_MEMORY_PROFILE = "in-memory";

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TbaTaskApplication.class, args);
	}
}
