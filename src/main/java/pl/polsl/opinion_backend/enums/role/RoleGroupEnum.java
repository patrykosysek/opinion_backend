package pl.polsl.opinion_backend.enums.role;

import lombok.AllArgsConstructor;
import pl.polsl.opinion_backend.entities.role.RoleGroup;

import java.util.Set;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_ALL;


@AllArgsConstructor
public enum RoleGroupEnum {
    ADMIN(Set.of(
            ROLE_ALL
    )),
    OPINION_USER(Set.of(

    ));


    public final Set<String> roles;

    public static Set<String> getRoles(String roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup).roles;
    }

    public static RoleGroupEnum getEnum(RoleGroup roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup.getName());
    }
}