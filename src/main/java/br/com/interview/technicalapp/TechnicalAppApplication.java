package br.com.interview.technicalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.com.interview.technicalapp")
@EntityScan(basePackages = "br.com.interview.technicalapp.*.model")
public class TechnicalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalAppApplication.class, args);
	}

}
