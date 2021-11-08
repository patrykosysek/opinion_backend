package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.repositories.list.seen.MovieSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_SEEN_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieSeenListService extends BasicService<MovieSeenList, MovieSeenListRepository> {

    @Override
    public MovieSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_SEEN_LIST_NOT_FOUND));
    }

    public boolean existsBySeenListAndMovie(SeenList seenList, Movie movie) {
        return repository.existsBySeenListAndMovie(seenList, movie);
    }

    public MovieSeenList findByMovieIdAndSeenList(UUID movieId, SeenList seenList) {
        return repository.findByMovie_IdAndSeenList(movieId, seenList).orElseThrow(() -> new IllegalArgumentException(MOVIE_SEEN_LIST_NOT_FOUND));
    }

    public Set<MovieSeenList> findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MovieSeenList> findAllByMovieIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByMovie_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MovieSeenList> findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<MovieSeenList> findAllByMovieGenresAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByMovieGenresNameAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

    public Set<MovieSeenList> findAllByGenresName(String genre) {
        return repository.findAllByMovieGenresName(genre);
    }

}
