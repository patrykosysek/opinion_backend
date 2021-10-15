package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private final UUID id;
    private final String email;

}