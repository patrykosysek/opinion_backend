package pl.polsl.opinion_backend.validators.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.EMAIL_IS_ALREADY_TAKEN;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default EMAIL_IS_ALREADY_TAKEN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
