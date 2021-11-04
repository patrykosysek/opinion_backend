package pl.polsl.opinion_backend.services.works;

import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.repositories.works.WorkOfCultureRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;


public abstract class WorkOfCultureService<T extends WorkOfCulture, R extends WorkOfCultureRepository<T, UUID>> extends BasicService<T, R> {

    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    public boolean existsByApiId(String id) {
        return repository.existsByApiId(id);
    }

    public T getByApiId(String apiId) {
        return repository.findByApiId(apiId).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public Set<T> getAllByGenreName(String name) {
        return repository.findAllByGenres_Name(name);
    }

    public Set<T> getAll() {
        return repository.findAll();
    }

}