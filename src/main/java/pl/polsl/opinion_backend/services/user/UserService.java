package pl.polsl.opinion_backend.services.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.repositories.user.UserRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService extends BasicService<User, UserRepository> implements UserDetailsService {

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }


    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            if (authentication.getPrincipal() instanceof User)
                return (User) authentication.getPrincipal();

        throw new NoSuchElementException(USER_NOT_FOUND);
    }


}