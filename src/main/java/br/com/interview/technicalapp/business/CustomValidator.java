package br.com.interview.technicalapp.business;

import br.com.interview.technicalapp.recruiter.service.RecruiterService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomValidator implements ConstraintValidator<CustomValid, Object> {

    private Long fieldName;

    @Autowired
    private RecruiterService recruiterService;

    @Override
    public void initialize(CustomValid constraintAnnotation) {
        this.fieldName = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var hello = fieldName;
        var opa = value;
        return opa == hello;
    }

}
