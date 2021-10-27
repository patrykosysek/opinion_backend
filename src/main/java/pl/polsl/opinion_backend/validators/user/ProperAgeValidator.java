package pl.polsl.opinion_backend.validators.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProperAgeValidator implements ConstraintValidator<ProperAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        return dateOfBirth == null || olderThan13(dateOfBirth);
    }

    private boolean olderThan13(LocalDate dateOfBirth) {
        LocalDate now = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(dateOfBirth, now);
        return age >= 13;
    }

}
