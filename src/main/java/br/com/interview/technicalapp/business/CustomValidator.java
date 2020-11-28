package br.com.interview.technicalapp.business;

import static javax.validation.constraintvalidation.ValidationTarget.PARAMETERS;

import br.com.interview.technicalapp.recruiter.service.RecruiterService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;

import org.springframework.beans.factory.annotation.Autowired;

@SupportedValidationTarget({PARAMETERS})
public class CustomValidator implements ConstraintValidator<CustomValid, Object> {

    private String fieldName;

    @Autowired
    private RecruiterService recruiterService;

    @Override
    public void initialize(CustomValid constraintAnnotation) {
        this.fieldName = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return true;
    }

}
