package ir.amv.snippets.tbatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TbaTaskApplication {

	public static final String IN_MEMORY_PROFILE = "in-memory";
	public static void main(String[] args) {
		SpringApplication.run(TbaTaskApplication.class, args);
	}
}
