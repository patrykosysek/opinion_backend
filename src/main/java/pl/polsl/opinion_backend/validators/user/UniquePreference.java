package pl.polsl.opinion_backend.validators.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.PREFERENCE_NOT_UNIQUE;

@Documented
@Constraint(validatedBy = UniquePreferenceValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePreference {

    String message() default PREFERENCE_NOT_UNIQUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
