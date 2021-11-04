package pl.polsl.opinion_backend.repositories.works;

import org.springframework.data.repository.NoRepositoryBean;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public interface WorkOfCultureRepository<T extends WorkOfCulture, ID extends Serializable> extends BasicRepository<T, ID> {

    boolean existsByTitle(String title);

    boolean existsByApiId(String id);

    Optional<T> findByApiId(String id);

    Set<T> findAllByGenres_Name(String name);

    Set<T> findAll();

}