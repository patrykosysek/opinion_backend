package pl.polsl.opinion_backend.configuration.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = JwtStatics.SECURITY_SCHEME_NAME,
        paramName = JwtStatics.AUTHORIZATION_HEADER,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "Bearer <token>"
)
public class SwaggerConfiguration {
}
