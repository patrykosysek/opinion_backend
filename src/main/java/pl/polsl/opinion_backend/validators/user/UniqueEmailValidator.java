package pl.polsl.opinion_backend.validators.user;

import lombok.RequiredArgsConstructor;
import pl.polsl.opinion_backend.services.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email == null || isUniqueEmail(email);
    }

    private boolean isUniqueEmail(String email) {
        String formattedEmail = email.trim().toLowerCase();
        return !userService.existsByEmail(formattedEmail);
    }

}
