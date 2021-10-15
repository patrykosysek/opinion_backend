package pl.polsl.opinion_backend.enums.role;

import lombok.AllArgsConstructor;
import pl.polsl.opinion_backend.entities.role.RoleGroup;

import java.util.Set;

import static pl.polsl.opinion_backend.enums.role.Roles.*;


@AllArgsConstructor
public enum RoleGroupEnum {
    ADMIN(Set.of(
            ROLE_USER_CREATE,
            ROLE_USER_DELETE,
            ROLE_USER_UPDATE,
            ROLE_USER_READ,
            ROLE_USER_LOCK,
            ROLE_FACILITY_SPECIALIST_CREATE,
            ROLE_FACILITY_SPECIALIST_DELETE,
            ROLE_FACILITY_SPECIALIST_UPDATE,
            ROLE_FACILITY_SPECIALIST_READ,
            ROLE_FACILITY_CREATE,
            ROLE_FACILITY_DELETE,
            ROLE_FACILITY_UPDATE,
            ROLE_FACILITY_READ
    )),
    PATIENT(Set.of(
            ROLE_USER_CREATE,
            ROLE_USER_DELETE,
            ROLE_USER_UPDATE)
    ),
    SPECIALIST(Set.of(
            ROLE_USER_CREATE,
            ROLE_USER_DELETE,
            ROLE_USER_UPDATE
    )),
    OWNER(Set.of(
            ROLE_USER_CREATE,
            ROLE_USER_DELETE,
            ROLE_USER_UPDATE
    ));

    public final Set<String> roles;

    public static Set<String> getRoles(String roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup).roles;
    }

    public static RoleGroupEnum getEnum(RoleGroup roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup.getName());
    }
}