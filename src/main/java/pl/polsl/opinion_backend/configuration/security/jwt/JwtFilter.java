package pl.polsl.opinion_backend.configuration.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;

import static java.util.function.Predicate.not;
import static pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics.AUTHORIZATION_HEADER;
import static pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics.BEARER_PATTERN;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    //    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        getToken(request)
                .map(jwtUtils::getUserFromJwt)
                .map(user -> JwtPreAuthenticationToken
                        .builder()
                        .user(user)
                        .details(new WebAuthenticationDetailsSource().buildDetails(request))
                        .build())
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(not(String::isEmpty))
                .map(BEARER_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1));
    }
}
