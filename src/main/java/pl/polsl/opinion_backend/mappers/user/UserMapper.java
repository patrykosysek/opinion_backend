package pl.polsl.opinion_backend.mappers.user;

import org.mapstruct.*;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtFactory;
import pl.polsl.opinion_backend.dtos.user.UserCreateDTO;
import pl.polsl.opinion_backend.dtos.user.UserResponseDTO;
import pl.polsl.opinion_backend.dtos.user.UserUpdateDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.AgeMapping;
import pl.polsl.opinion_backend.mappers.qualifires.RoleGroupMapping;
import pl.polsl.opinion_backend.services.role.RoleGroupService;

@Mapper(uses = {JwtFactory.class, PreferenceMapper.class, AgeMapper.class, RoleGroupService.class}, componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "age", source = "dateOfBirth", qualifiedBy = AgeMapping.class)
    @Mapping(target = "roleGroups", source = "dateOfBirth", qualifiedBy = RoleGroupMapping.class)
    User toUser(UserCreateDTO userCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(@MappingTarget User user, UserUpdateDTO dto);

    @AfterMapping
    default void trimEmailToLowerCase(@MappingTarget User user) {
        String email = user.getEmail().trim().toLowerCase();
        user.setEmail(email);
    }

}
