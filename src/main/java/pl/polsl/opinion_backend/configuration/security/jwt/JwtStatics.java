package pl.polsl.opinion_backend.configuration.security.jwt;


import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static lombok.AccessLevel.NONE;

@NoArgsConstructor(access = NONE)
public class JwtStatics {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");
    public static final String SECURITY_SCHEME_NAME = "JWT access token";

}