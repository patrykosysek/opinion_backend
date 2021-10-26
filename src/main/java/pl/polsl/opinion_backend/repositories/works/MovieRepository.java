package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;

import java.util.UUID;

@Repository
public interface MovieRepository extends WorkOfCultureRepository<Movie, UUID> {
}
