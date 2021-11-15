package pl.polsl.opinion_backend.configuration.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SecurityEndpoints {
    AUTH_URL("/auth/**", false),
    REGISTRATION_URL("/registration/**", false),
    VERIFICATION_RESET_PASSWORD_URL("/reset-password/**", false),
    GIT_INFO_URL("/info/**", false),

    //swagger
    CONFIGURATION_URL("/configuration/ui", false),
    CONFIGURATION_SECURITY_URL("/configuration/security", false),
    SWAGGER_RESOURCES_URL("/swagger-resources/**", false),
    SWAGGER_UI_HTML_URL("/swagger-ui.html", false),
    SWAGGER_UI_URL("/swagger-ui/**", false),
    WEBJARS_URL("/webjars/**", false),
    V2_URL("/v2/**", false),
    V3_URL("/v3/**", false),

    RECOMMENDATION("/work-of-culture/{workOfCultureType}", false),
    RECOMMENDED("/work-of-culture/recommendation", false),
    FILTRATION("/work-of-culture/{workOfCultureType}/filter", false),
    REGISTRATION("/users/registration", false);

    public final String value;
    public final boolean authentication;
}
