package pl.polsl.opinion_backend.configuration.tokenSigningKey;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TokenSigningKeyProviderCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return envOrSystemProperty("TOKEN_SIGNING_KEY", "jwt.tokenSigningKey");
    }

    private boolean envOrSystemProperty(String env, String prop) {
        return StringUtils.isNotEmpty(System.getProperty(prop)) || StringUtils.isNotEmpty(System.getenv(env));
    }
}
