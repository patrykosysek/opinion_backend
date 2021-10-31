package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.list.watch.MovieWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_WATCH_LIST_NOT_FOUND;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

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

}
