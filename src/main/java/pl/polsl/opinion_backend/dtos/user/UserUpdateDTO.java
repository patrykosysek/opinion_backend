package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.validators.user.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @Email(message = EMAIL_INVALID)
    @UniqueEmail
    private String email;

    @Size(min = 5, max = 10, message = NICKNAME_SIZE)
    private String nickname;

    @Size(min = 5, max = 10, message = PASSWORD_SIZE)
    private String password;

    private GenreType favouriteGenre;

}
