package br.com.interview.technicalapp.recruiter.controller.v1.dto

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import java.util.stream.Collectors

import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class RecruiterRequestSpec extends Specification {

    private Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def "validate recruiter request"() {
        given: "an recruiter"
        RecruiterRequest recruiter = new RecruiterRequest()

        when: "set values from datatable"
        recruiter.setUsername(username)
        recruiter.setEmail(email)
        recruiter.setPassword(password)
        and: "validate"
        Set<ConstraintViolation<RecruiterRequest>> violations = validator.validate(recruiter)
        and: "get the properties"
        List<String> violationsProperties = violations.stream()
                .map(k -> k.getPropertyPath() as String)
                .collect(Collectors.toList())
        violationsProperties.sort()

        then: "compare the errors from datatable"
        violationsProperties == errors

        where:
        username                             | email                | password         || errors
        "whispers"                           | "email@email.com"    | "mysecret"       || []
        "colo"                               | "oax65732@eoopy.com" | "palemist16"     || []
        "jade"                               | "email@email.com"    | "jadecoyote86"   || []
        "defo"                               | "defo@hotmail.com"   | "happymonster24" || []
        "!!!!"                               | "!!!@!!!.com"        | "!!!!!"          || []
        "jabin"                              | "email"              | "uglylynx89"     || ["email"]
        "dubov"                              | "myemail"            | "goldfire74"     || ["email"]
        "randon"                             | "e@a"                | "mysecret"       || ["email"]
        "foob"                               | null                 | "freeisland52"   || ["email"]
        "lagrave"                            | "ema"                | "mysecret"       || ["email", "email"]
        "iasjndcoiuhsijtisjncsdjicnwsersdca" | "email@email.com"    | "poorwave16"     || ["username"]
        "bar"                                | "email@email.com"    | "1234"           || ["username"]
        "123"                                | "email@email.com"    | "123"            || ["password", "username"]
        "q"                                  | "w"                  | "e"              || ["email", "email", "password", "username"]
        "a"                                  | null                 | "b"              || ["email", "password", "username"]
        null                                 | null                 | "pass"           || ["email", "username"]
        null                                 | null                 | null             || ["email", "password", "username"]

    }
}
