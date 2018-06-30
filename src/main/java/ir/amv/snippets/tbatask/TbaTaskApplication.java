package ir.amv.snippets.tbatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application configuration.
 */
@SpringBootApplication
public class TbaTaskApplication {

	/**
	 * profile used for in-memory implementation. if we decide to keep data in another repository we can introduce a
	 * new profile and new implementation for those beans which are using this profile.
	 */
	public static final String IN_MEMORY_PROFILE = "in-memory";

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TbaTaskApplication.class, args);
	}
}
