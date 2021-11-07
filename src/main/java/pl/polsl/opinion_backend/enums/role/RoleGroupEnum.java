package pl.polsl.opinion_backend.enums.role;

import lombok.AllArgsConstructor;
import pl.polsl.opinion_backend.entities.role.RoleGroup;

import java.util.Set;

import static pl.polsl.opinion_backend.enums.role.Roles.*;


@AllArgsConstructor
public enum RoleGroupEnum {
    ADMIN(Set.of(
            ROLE_ALL,
            ROLE_SEEN_LIST,
            ROLE_WATCH_LIST,
            ROLE_USER_ALL,
            ROLE_WORK_OF_CULTURE_ALL,
            ROLE_DISCUSSION,
            ROLE_WORK_OF_CULTURE_RECOMMENDATION_PREFERENCE,
            ROLE_WORK_OF_CULTURE_READ,
            ROLE_WORK_OF_CULTURE_STATISTIC
    )),
    OPINION_USER(Set.of(
            ROLE_SEEN_LIST,
            ROLE_WATCH_LIST,
            ROLE_DISCUSSION,
            ROLE_WORK_OF_CULTURE_RECOMMENDATION_PREFERENCE,
            ROLE_WORK_OF_CULTURE_READ
    ));


    public final Set<String> roles;

    public static Set<String> getRoles(String roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup).roles;
    }

    public static RoleGroupEnum getEnum(RoleGroup roleGroup) {
        return RoleGroupEnum.valueOf(roleGroup.getName());
    }
}