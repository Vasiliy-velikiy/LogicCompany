package com.moskalev.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 21.02.22
 * Annotation for email
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {

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

