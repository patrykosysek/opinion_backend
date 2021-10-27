package pl.polsl.opinion_backend.mappers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.mappers.qualifires.EncodedPasswordMapping;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {
    private final PasswordEncoder passwordEncoder;

    @EncodedPasswordMapping
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
