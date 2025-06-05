package pe.edu.upc.MLTDAH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MltdahApplication {

	public static void main(String[] args) {
		SpringApplication.run(MltdahApplication.class, args);
	}

}
