package pl.polsl.opinion_backend.dtos.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REFRESH_TOKEN_REQUIRED;


@Data
public class RefreshTokenDTO {
    @NotBlank(message = REFRESH_TOKEN_REQUIRED)
    private String refreshToken;
}
