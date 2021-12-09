package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.works.WorkOfCultureRepository;

import java.util.UUID;

@Repository
public interface MovieRepository extends WorkOfCultureRepository<Movie, UUID> {
}
