package pl.polsl.opinion_backend.services.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.repositories.role.RoleGroupRepository;
import pl.polsl.opinion_backend.services.basic.AbstractBaseService;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ROLE_GROUP_NOT_FOUND;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RoleGroupService extends AbstractBaseService<RoleGroup, RoleGroupRepository> {

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

}