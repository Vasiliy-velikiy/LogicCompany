package com.moskalev.validation;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 21.02.22
 * Validator for  registrationNumber
 */
public class RegistrationNumberValidation implements ConstraintValidator <RegistrationNumberAnnotation,String>{

    /**
     * pass data in annotation
     */
    @Override
    public void initialize(RegistrationNumberAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * @param value -email
     * @param constraintValidatorContext-context where makes validation
     * @return valid or not valid
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.matches("[a-z0-9]*");
    }
}
