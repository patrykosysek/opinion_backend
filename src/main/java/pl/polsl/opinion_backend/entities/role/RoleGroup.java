package pl.polsl.opinion_backend.entities.role;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.AbstractBaseEntity;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class RoleGroup extends AbstractBaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    public RoleGroup(RoleGroupEnum roleGroupEnum) {
        this.name = roleGroupEnum.name();
    }

    public RoleGroup(String roleName) {
        this.name = roleName;
    }
}

