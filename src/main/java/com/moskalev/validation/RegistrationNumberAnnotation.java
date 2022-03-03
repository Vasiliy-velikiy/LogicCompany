package com.moskalev.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegistrationNumberValidation.class)
@Documented
public @interface RegistrationNumberAnnotation {

    /**
     * Show this message if email not valid
     */
    String message() default "This email not valid";

    /**
     * method for metaInformation
     */
    Class<?>[] groups() default {};

    /**
     * method for metaInformation
     */
    Class<? extends Payload>[] payload() default {};
}
