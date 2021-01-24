package br.com.interview.technicalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ApplicationContext.class)
@ActiveProfiles(profiles = "test")
class TechnicalAppApplicationTests {

	@Test
	void contextLoads() {
        Assertions.assertTrue(true);
	}

}
