package ua.in.kp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {FutureDateValidator.class})
public @interface FutureDate {
    String message() default "{validation.constraint.future.date.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
