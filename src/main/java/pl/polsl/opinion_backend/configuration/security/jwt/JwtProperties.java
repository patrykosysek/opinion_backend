package pl.polsl.opinion_backend.configuration.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("jwt")
@Getter
@AllArgsConstructor
public class JwtProperties {

    private final Integer tokenExpirationTime;

    private final String tokenIssuer;

    private final Integer refreshTokenExpTime;
}
