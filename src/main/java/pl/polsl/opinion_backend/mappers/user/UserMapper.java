package pl.polsl.opinion_backend.mappers.user;

import org.mapstruct.Mapper;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.user.UserResponseDTO;
import pl.polsl.opinion_backend.entities.user.User;

@Mapper(uses = {JwtFactory.class}, componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);

}
