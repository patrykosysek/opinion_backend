package pl.polsl.opinion_backend.configuration.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.polsl.opinion_backend.entities.user.User;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditorAware implements AuditorAware<UUID> {

    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User)) {
            return Optional.empty();
        }

        return Optional.of(((User) authentication.getPrincipal()).getId());
    }
}