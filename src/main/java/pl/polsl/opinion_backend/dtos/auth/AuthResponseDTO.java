package pl.polsl.opinion_backend.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.dtos.user.UserResponseDTO;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    UserResponseDTO user;
    String accessToken;
    String refreshToken;
    RoleGroupEnum role;
}
