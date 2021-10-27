package pl.polsl.opinion_backend.validators.user;

import lombok.RequiredArgsConstructor;
import pl.polsl.opinion_backend.dtos.user.PreferenceCreateDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@RequiredArgsConstructor
public class UniquePreferenceValidator implements ConstraintValidator<UniquePreference, Set<PreferenceCreateDTO>> {

    @Override
    public boolean isValid(Set<PreferenceCreateDTO> preferences, ConstraintValidatorContext constraintValidatorContext) {
        return preferences == null || arePreferencesUnique(preferences);
    }

    private boolean arePreferencesUnique(Set<PreferenceCreateDTO> preferences) {

        for (WorkOfCultureType w : WorkOfCultureType.values()) {
            int i = 0;
            for (PreferenceCreateDTO p : preferences) {
                if (p.getWorkOfCultureType().equals(w))
                    i++;
            }
            if (i > 1)
                return false;

        }
        return true;
    }

}
