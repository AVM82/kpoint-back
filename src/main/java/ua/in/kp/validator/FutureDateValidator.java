package ua.in.kp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FutureDateValidator implements ConstraintValidator<FutureDate, String> {
    @Override
    public void initialize(FutureDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            return !date.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

}
