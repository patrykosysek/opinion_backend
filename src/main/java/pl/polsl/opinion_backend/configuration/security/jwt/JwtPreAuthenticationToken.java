package pl.polsl.opinion_backend.configuration.security.jwt;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import pl.polsl.opinion_backend.entities.user.User;

@Getter
public class JwtPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {
    private static final long serialVersionUID = -5304730621727936850L;

    @Builder
    public JwtPreAuthenticationToken(User user, WebAuthenticationDetails details) {
        super(user, null, user.getAuthorities());
        super.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
