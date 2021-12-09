package pl.polsl.opinion_backend.services.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;
import pl.polsl.opinion_backend.mappers.qualifires.RoleGroupMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UserRoleGroupMapping;
import pl.polsl.opinion_backend.repositories.role.RoleGroupRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.*;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ROLE_GROUP_NOT_FOUND;

@Service
@AllArgsConstructor
public class RoleGroupService extends BasicService<RoleGroup, RoleGroupRepository> {

    public RoleGroup getByRoleName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NoSuchElementException(ROLE_GROUP_NOT_FOUND));
    }

    public void deleteByName(String roleGroupName) {
        repository.deleteByName(roleGroupName);
    }

    public Set<RoleGroup> getAll() {
        return repository.findAll();
    }

    @Override
    public RoleGroup getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ROLE_GROUP_NOT_FOUND));
    }

    @RoleGroupMapping
    public Set<RoleGroup> getOpinionUser(Object o) {
        Set<RoleGroup> roleGroups = new HashSet<>();
        roleGroups.add(getByRoleName("OPINION_USER"));
        return roleGroups;
    }

    @UserRoleGroupMapping
    public RoleGroupEnum getOpinionUserRole(Set<RoleGroup> roleGroups) {
        Optional<RoleGroup> roleGroup = roleGroups.stream().findFirst();
        return RoleGroupEnum.valueOf(roleGroup.get().getName());
    }

}