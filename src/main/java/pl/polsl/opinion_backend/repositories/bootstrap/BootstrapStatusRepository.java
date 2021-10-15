package pl.polsl.opinion_backend.repositories.bootstrap;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.bootstrap.BootstrapStatus;
import pl.polsl.opinion_backend.repositories.base.BasePagingRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BootstrapStatusRepository extends BasePagingRepository<BootstrapStatus, UUID> {

    Optional<BootstrapStatus> findFirstByDone(boolean done);

}
