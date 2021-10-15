package pl.polsl.opinion_backend.dtos.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;


@Data
public class LoginDTO {

    @Email(message = EMAIL_INVALID)
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;
    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;

    public String getEmail() {
        return email.trim().toLowerCase();
    }
}
