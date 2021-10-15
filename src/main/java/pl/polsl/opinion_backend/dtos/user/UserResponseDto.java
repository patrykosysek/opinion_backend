package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private final UUID id;

    private final String email;

    private final boolean enabled;

}