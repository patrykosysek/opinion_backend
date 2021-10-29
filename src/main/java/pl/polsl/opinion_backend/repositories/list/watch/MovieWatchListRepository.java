package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieWatchListRepository extends BasicRepository<MovieWatchList, UUID> {

    boolean existsByWatchListAndMovie(WatchList watchList, Movie movie);

    boolean existsByWatchListAndMovie_Id(WatchList watchList, UUID movieId);

    Optional<MovieWatchList> findByMovie_IdAndWatchList(UUID movieId, WatchList watchList);

}
