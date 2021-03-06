package br.com.interview.technicalapp

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles

import groovy.transform.CompileStatic
import spock.lang.Specification

@SpringBootTest(classes = ApplicationContext.class)
@ActiveProfiles("test")
@CompileStatic
class TechnicalAppApplicationSpec extends Specification {

    def "should load the context"() {
        expect:
        true
    }

}
