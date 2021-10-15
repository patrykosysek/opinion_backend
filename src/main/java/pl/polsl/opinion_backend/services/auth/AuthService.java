package pl.polsl.opinion_backend.services.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtUtils;
import pl.polsl.opinion_backend.dtos.auth.AuthResponseDTO;
import pl.polsl.opinion_backend.dtos.auth.RefreshTokenDTO;
import pl.polsl.opinion_backend.dtos.auth.RefreshTokenResponseDTO;
import pl.polsl.opinion_backend.dtos.auth.SignInDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.auth.AuthMapper;
import pl.polsl.opinion_backend.services.user.UserService;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.SIGN_IN_INPUT_INVALID;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_ENABLED;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;
    private final JwtUtils jwtUtils;

    @Transactional(noRollbackFor = {IllegalArgumentException.class, LockedException.class})
    public AuthResponseDTO signIn(SignInDTO input) {
        User user = authenticate(input);

        return authMapper.toAuthResponseDto(user);
    }

    private User authenticate(SignInDTO input) {
        User user = userService.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(SIGN_IN_INPUT_INVALID));


        if (!user.isEnabled()) {
            throw new DisabledException(USER_NOT_ENABLED);
        }

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(SIGN_IN_INPUT_INVALID);
        }

        return user;
    }


    public RefreshTokenResponseDTO refreshToken(RefreshTokenDTO input) {
        User user = jwtUtils.getUserFromJwt(input.getRefreshToken());

        return new RefreshTokenResponseDTO(jwtFactory.createAccessToken(user));
    }
}
//    public void authenticationSucceeded(User user) {
//        LockStatus lockStatus = user.getLockStatus();
//        lockStatus.resetLoginFailedAttempts();
//        lockStatus.saveLastLoginDate();
//
//    }

//    public void authenticationFailed(User user) {
//        user.getLockStatus().incrementLoginFailedAttempts();
//        if (user.getLockStatus().getLoginFailedAttempts() == loginAttemptsProperties.getMaxFailedAttempts()) {
//            userService.lockUser(user);
//            throw new LockedException(USER_LOCKED);
//        }
//
//    }


