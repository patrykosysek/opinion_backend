package pl.polsl.opinion_backend.mappers.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.auth.AuthResponseDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.JwtAccessTokenMapping;
import pl.polsl.opinion_backend.mappers.qualifires.JwtRefreshTokenMapping;
import pl.polsl.opinion_backend.mappers.user.UserMapper;

@Mapper(uses = {JwtFactory.class, UserMapper.class}, componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "accessToken", source = "user", qualifiedBy = JwtAccessTokenMapping.class)
    @Mapping(target = "refreshToken", source = "user", qualifiedBy = JwtRefreshTokenMapping.class)
    AuthResponseDTO toAuthResponseDto(User user);

}
