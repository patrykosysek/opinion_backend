package pl.polsl.opinion_backend.services.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.polsl.opinion_backend.entities.base.AbstractBaseEntity;
import pl.polsl.opinion_backend.repositories.base.BasePagingRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractBaseService<E extends AbstractBaseEntity, R extends BasePagingRepository<E, UUID>> {

    @Autowired
    protected R repository;

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<E> findAll(Specification<E> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    public abstract E getById(UUID id);

    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<E> findById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.delete(getById(id));
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }


}
