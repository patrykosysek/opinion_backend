package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.list.watch.MovieWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_WATCH_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieWatchListService extends BasicService<MovieWatchList, MovieWatchListRepository> {

    @Override
    public MovieWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_WATCH_LIST_NOT_FOUND));
    }

    public boolean existsByWatchListAndMovie(WatchList watchList, Movie movie) {
        return repository.existsByWatchListAndMovie(watchList, movie);
    }


    public boolean existsByWatchListAndMovieId(WatchList watchList, UUID movieId) {
        return repository.existsByWatchListAndMovie_Id(watchList, movieId);
    }

    public MovieWatchList findByMovieIdAndWatchList(UUID movieId, WatchList watchList) {
        return repository.findByMovie_IdAndWatchList(movieId, watchList).orElseThrow(() -> new IllegalArgumentException(MOVIE_WATCH_LIST_NOT_FOUND));
    }

    public Set<MovieWatchList> findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MovieWatchList> findAllByMovieIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByMovie_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MovieWatchList> findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<MovieWatchList> findAllByMovieGenresAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByMovieGenresNameAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

    public Set<MovieWatchList> findAllByGenresName(String genre) {
        return repository.findAllByMovieGenresName(genre);
    }

}
