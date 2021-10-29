package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieSeenListRepository extends BasicRepository<MovieSeenList, UUID> {

    boolean existsBySeenListAndMovie(SeenList seenList, Movie movie);

    Optional<MovieSeenList> findByMovie_IdAndSeenList(UUID movieId, SeenList seenList);

}
