package pl.polsl.opinion_backend.configuration.security.jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.exceptions.ErrorMessages;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtUtils {

    private final JWTVerifier verifier;
    private final UserService userService;

    public Optional<DecodedJWT> getDecodedJwt(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch (TokenExpiredException ex) {
            throw new JWTVerificationException(ErrorMessages.JWT_EXPIRED);
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException(ErrorMessages.JWT_INVALID);
        }
    }

    public User getUserFromJwt(String jwt) {
        return getDecodedJwt(jwt)
                .map(DecodedJWT::getSubject)
                .flatMap(userService::findById)
                .orElseThrow(() -> new JWTVerificationException(ErrorMessages.USER_INVALID));
    }
}
