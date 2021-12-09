package pl.polsl.opinion_backend.mappers.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.auth.AuthResponseDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.JwtAccessTokenMapping;
import pl.polsl.opinion_backend.mappers.qualifires.JwtRefreshTokenMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UserRoleGroupMapping;
import pl.polsl.opinion_backend.mappers.user.UserMapper;
import pl.polsl.opinion_backend.services.role.RoleGroupService;

@Mapper(uses = {JwtFactory.class, UserMapper.class, RoleGroupService.class}, componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "accessToken", source = "user", qualifiedBy = JwtAccessTokenMapping.class)
    @Mapping(target = "refreshToken", source = "user", qualifiedBy = JwtRefreshTokenMapping.class)
    @Mapping(target = "role", source = "roleGroups", qualifiedBy = UserRoleGroupMapping.class)
    AuthResponseDTO toAuthResponseDto(User user);

}
