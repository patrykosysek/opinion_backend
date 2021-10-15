package pl.polsl.opinion_backend.mappers.auth;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.auth.AuthResponseDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.JwtAccessTokenMapping;
import pl.polsl.opinion_backend.mappers.qualifires.JwtRefreshTokenMapping;

@Mapper(uses = {JwtFactory.class}, componentModel = "spring")
public interface AuthMapper {
    //private final JwtFactory jwtFactory;

//    public AuthResponseDTO toAuthResponseDto(User user) {
//        return new AuthResponseDTO(
//                user,
//                jwtFactory.createAccessToken(user),
//                jwtFactory.createRefreshToken(user)
//        );
//    }

    @Mapping(target = "user", source = "user")
    @Mapping(target = "accessToken", source = "user", qualifiedBy = JwtAccessTokenMapping.class)
    @Mapping(target = "refreshToken", source = "user", qualifiedBy = JwtRefreshTokenMapping.class)
    AuthResponseDTO toAuthResponseDto(User user);

}
