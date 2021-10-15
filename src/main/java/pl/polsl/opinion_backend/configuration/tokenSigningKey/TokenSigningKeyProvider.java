package pl.polsl.opinion_backend.configuration.tokenSigningKey;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Conditional(TokenSigningKeyProviderCondition.class)
public class TokenSigningKeyProvider {

    private final String tokenSigningKey;

    public TokenSigningKeyProvider(@Value("${TOKEN_SIGNING_KEY:none}") String signingKeyEnv,
                                   @Value("${jwt.tokenSigningKey:none}") String signingKeyProp) {
        this.tokenSigningKey = signingKeyEnv != null && !signingKeyEnv.equals("none") ? signingKeyEnv : signingKeyProp;
    }
}
