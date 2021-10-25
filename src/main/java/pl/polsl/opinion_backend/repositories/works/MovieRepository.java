package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.Movie;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends BasicRepository<Movie, UUID> {

    boolean existsByTitle(String title);

    boolean existsByApiId(String id);

    Optional<Movie> findByApiId(String id);

}
