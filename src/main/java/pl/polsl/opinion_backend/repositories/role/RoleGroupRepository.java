package pl.polsl.opinion_backend.repositories.role;


import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.repositories.base.BasePagingRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleGroupRepository extends BasePagingRepository<RoleGroup, UUID> {

    Optional<RoleGroup> findByName(String name);

    void deleteByName(String name);

    Set<RoleGroup> findAll();
}
