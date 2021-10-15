package pl.polsl.opinion_backend.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.entities.user.User;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    User user;
    String accessToken;
    String refreshToken;
}
