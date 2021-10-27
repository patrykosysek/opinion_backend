package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.validators.user.ProperAge;
import pl.polsl.opinion_backend.validators.user.UniqueEmail;
import pl.polsl.opinion_backend.validators.user.UniquePreference;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@Data
@AllArgsConstructor
public class UserCreateDTO {

    @Email(message = EMAIL_INVALID)
    @NotBlank(message = EMAIL_REQUIRED)
    @UniqueEmail
    private String email;

    @NotBlank(message = NICKNAME_REQUIRED)
    @Size(min = 5, max = 10, message = NICKNAME_SIZE)
    private String nickname;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Size(min = 5, max = 10, message = PASSWORD_SIZE)
    private String password;

    @NotNull(message = FAVOURITE_GENRE_REQUIRED)
    private GenreType favouriteGenre;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = DATE_OF_BIRTH_REQUIRED)
    @Past(message = DATE_OF_BIRTH_INVALID)
    @ProperAge
    private LocalDate dateOfBirth;

    @Size(max = 5, message = PREFERENCE_SIZE)
    @UniquePreference
    private Set<PreferenceCreateDTO> preferences;

}
