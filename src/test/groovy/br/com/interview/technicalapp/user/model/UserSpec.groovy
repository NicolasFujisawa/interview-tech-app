package br.com.interview.technicalapp.user.model

import br.com.interview.technicalapp.recruiter.model.Recruiter

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import groovy.transform.CompileDynamic
import spock.lang.Specification
import spock.lang.Unroll

@CompileDynamic
class UserSpec extends Specification {

    private Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    @Unroll
    def "validation recruiter #username, #password, #email must have #size errors"() {
        given: "an recruiter"
        Recruiter recruiter = new Recruiter()

        when: "populate with datatable values"
        recruiter.setUsername(username)
        recruiter.setPassword(password)
        recruiter.setEmail(email)
        and: "get violations"
        Set<ConstraintViolation<Recruiter>> violations = validator.validate(recruiter)

        then: "judge the size only"
        violations.size() == size

        where:
        username  | password | email             || size
        "jatoba"  | "java"   | "email@email"     || 0
        "cabo"    | "groovy" | "email@email.com" || 0
        "polo"    | "gradle" | "email"           || 0
        "bispo"   | "maven"  | null              || 1
        "intelli" | "sense"  | null              || 1
        "jabin"   | "c++"    | null              || 1
        "1"       | null     | null              || 2
        "util"    | null     | null              || 2
        "#\$!@"   | null     | null              || 2
        null      | null     | null              || 3
    }

}
