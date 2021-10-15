package pl.polsl.opinion_backend.services.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.bootstrap.BootstrapStatus;
import pl.polsl.opinion_backend.repositories.bootstrap.BootstrapStatusRepository;
import pl.polsl.opinion_backend.services.basic.AbstractBaseService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class BootstrapStatusService extends AbstractBaseService<BootstrapStatus, BootstrapStatusRepository> {

    @Override
    public BootstrapStatus getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("BOOTSTRAP_NOT_FOUND"));
    }

    public boolean isDone() {
        Optional<BootstrapStatus> bootstrapStatus = repository.findFirstByDone(true);
        return bootstrapStatus.isPresent();
    }

}
