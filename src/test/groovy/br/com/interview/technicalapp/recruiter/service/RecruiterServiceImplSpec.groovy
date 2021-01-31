package br.com.interview.technicalapp.recruiter.service

import br.com.interview.technicalapp.TechnicalAppApplication
import br.com.interview.technicalapp.recruiter.model.Recruiter

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback

import spock.lang.Specification

@SpringBootTest(classes = [ TechnicalAppApplication.class ])
@Transactional
class RecruiterServiceImplSpec extends Specification {

    @Autowired
    private RecruiterService recruiterService

    @Rollback
    def "create and delete a recruiter"() {
        given:
        Recruiter recruiter = new Recruiter()
        recruiter.setUsername("jabin")
        recruiter.setPassword("mysecret")
        recruiter.setEmail("my@email.com")

        when:
        Recruiter response = recruiterService.save(recruiter)

        then:
        response.getId() != null
        recruiter == response

        when:
        UUID uuid = response.getId()
        this.recruiterService.deleteById(uuid)
        List<Recruiter> recruiters = this.recruiterService.findAll()
        Optional<Recruiter> recruiterOptional = this.recruiterService.findById(uuid)

        then:
        recruiters.isEmpty()
        !recruiterOptional.isPresent()
    }

}
