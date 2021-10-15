package pl.polsl.opinion_backend.repositories.user;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.repositories.base.BasePagingRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BasePagingRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
