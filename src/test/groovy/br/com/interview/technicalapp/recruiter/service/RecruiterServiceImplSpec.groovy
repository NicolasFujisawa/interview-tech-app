package br.com.interview.technicalapp.recruiter.service

import br.com.interview.technicalapp.recruiter.model.Recruiter
import br.com.interview.technicalapp.recruiter.repository.RecruiterRepository

import spock.lang.Specification

class RecruiterServiceImplSpec extends Specification {

    private RecruiterService recruiterService
    private RecruiterRepository recruiterRepository

    def setup() {
        recruiterRepository = Stub(RecruiterRepository.class)
        recruiterService = new RecruiterServiceImpl(recruiterRepository: recruiterRepository)
    }

    def "must get optional recruiter"() {
        given:
        UUID uuid = UUID.randomUUID()
        Recruiter recruiter = new Recruiter()
        recruiter.setId(uuid)
        recruiter.setUsername("Joe")
        recruiterRepository.findById(uuid) >> Optional.of(recruiter)
        when:
        Optional<Recruiter> recruiterOptional = recruiterService.findById(uuid)
        then:
        recruiterOptional.isPresent()
        recruiterOptional.get() == recruiter
    }

}
