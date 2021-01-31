package br.com.interview.technicalapp.recruiter.service;

import br.com.interview.technicalapp.TechnicalAppApplication;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { TechnicalAppApplication.class })
class RescruiterServiceTest {

    @Autowired
    private RecruiterService recruiterService;

    @Test
    void createAndFindUserWithH2() {
        Recruiter recruiter = new Recruiter();
        recruiter.setUsername("onio");
        recruiter.setEmail("my@email.com");
        recruiter.setPassword("mysecret");

        Recruiter response = recruiterService.save(recruiter);
        Optional<Recruiter> search = recruiterService.findById(response.getId());

        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(recruiter, response);
        Assertions.assertTrue(search.isPresent());
        Assertions.assertEquals(response.getId(), search.get().getId());
    }
}
