package pl.polsl.opinion_backend.configuration.security.jwt;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.JwtAccessTokenMapping;
import pl.polsl.opinion_backend.mappers.qualifires.JwtRefreshTokenMapping;
import pl.polsl.opinion_backend.configuration.tokenSigningKey.TokenSigningKeyProvider;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@ConditionalOnBean(TokenSigningKeyProvider.class)
public class JwtFactory {
    private final JwtProperties properties;
    private final TokenSigningKeyProvider tokenSigningKeyProvider;
    private final JwtBeans securityConfig;

    @JwtAccessTokenMapping
    public String createAccessToken(User user) {

        OffsetDateTime currentTime = OffsetDateTime.now();

        return JWT
                .create()
                .withIssuer(properties.getTokenIssuer())
                .withIssuedAt(Date.from(currentTime.toInstant()))
                .withExpiresAt(Date.from(currentTime
                        .plusMinutes(properties.getTokenExpirationTime())
                        .toInstant()))
                .withSubject(user.getId().toString())
                .sign(securityConfig.jwtAlgorithm(tokenSigningKeyProvider));
    }

    @JwtRefreshTokenMapping
    public String createRefreshToken(User user) {

        OffsetDateTime currentTime = OffsetDateTime.now();

        return JWT
                .create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer(properties.getTokenIssuer())
                .withIssuedAt(Date.from(currentTime.toInstant()))
                .withExpiresAt(Date.from(currentTime
                        .plusMinutes(properties.getRefreshTokenExpTime())
                        .toInstant()))
                .withSubject(user.getId().toString())
                .withArrayClaim("scopes", new String[]{"REFRESH_TOKEN"})
                .sign(securityConfig.jwtAlgorithm(tokenSigningKeyProvider));
    }
}
