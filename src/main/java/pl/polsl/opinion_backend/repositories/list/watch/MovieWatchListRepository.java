package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieWatchListRepository extends BasicRepository<MovieWatchList, UUID> {

    boolean existsByWatchListAndMovie(WatchList watchList, Movie movie);

    boolean existsByWatchListAndMovie_Id(WatchList watchList, UUID movieId);

    Optional<MovieWatchList> findByMovie_IdAndWatchList(UUID movieId, WatchList watchList);

    Set<MovieWatchList> findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieWatchList> findAllByMovie_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MovieWatchList> findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieWatchList> findAllByMovieGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<MovieWatchList> findAllByMovieGenresName(String name);

    Page<MovieWatchList> findAllByWatchList(WatchList watchList, Pageable pageable);

}
