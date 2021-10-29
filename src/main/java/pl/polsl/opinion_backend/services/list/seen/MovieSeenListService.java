package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.list.seen.MovieSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieSeenListService extends BasicService<MovieSeenList, MovieSeenListRepository> {

    @Override
    public MovieSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsBySeenListAndMovie(SeenList seenList, Movie movie) {
        return repository.existsBySeenListAndMovie(seenList, movie);
    }

    public MovieSeenList findByMovieIdAndSeenList(UUID movieId, SeenList seenList) {
        return repository.findByMovie_IdAndSeenList(movieId, seenList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
