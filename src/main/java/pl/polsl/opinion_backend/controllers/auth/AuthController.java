package pl.polsl.opinion_backend.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.dtos.auth.AuthResponseDTO;
import pl.polsl.opinion_backend.dtos.auth.RefreshTokenDTO;
import pl.polsl.opinion_backend.dtos.auth.RefreshTokenResponseDTO;
import pl.polsl.opinion_backend.dtos.auth.LoginDTO;
import pl.polsl.opinion_backend.services.auth.AuthService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Sign in")
    @ApiResponse(responseCode = "200", description = "User successfully signed in")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDTO signIn(@RequestBody @Valid LoginDTO input) {
        return authService.signIn(input);
    }

    @Operation(summary = "Refresh token")
    @ApiResponse(responseCode = "200", description = "New access token acquired")
    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponseDTO refreshToken(@RequestBody @Valid RefreshTokenDTO input) {
        return authService.refreshToken(input);
    }

}
