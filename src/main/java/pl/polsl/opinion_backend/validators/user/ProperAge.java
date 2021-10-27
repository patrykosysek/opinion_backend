package pl.polsl.opinion_backend.validators.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.AGE_NOT_PROPER;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProperAge {

    String message() default AGE_NOT_PROPER;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
