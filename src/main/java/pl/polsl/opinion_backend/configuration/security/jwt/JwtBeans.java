package pl.polsl.opinion_backend.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.opinion_backend.tokenSigningKey.TokenSigningKeyProvider;

@Configuration
public class JwtBeans {
    @Bean
    public Algorithm jwtAlgorithm(TokenSigningKeyProvider tokenSigningKeyProvider) {
        return Algorithm.HMAC256(tokenSigningKeyProvider.getTokenSigningKey());
    }

    @Bean
    public JWTVerifier verifier(JwtProperties properties, Algorithm algorithm) {
        return JWT
                .require(algorithm)
                .withIssuer(properties.getTokenIssuer())
                .build();
    }

}
