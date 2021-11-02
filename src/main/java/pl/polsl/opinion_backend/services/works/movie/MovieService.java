package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.works.movie.MovieRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieService extends WorkOfCultureService<Movie, MovieRepository> {

    @Override
    public Movie getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_NOT_FOUND));
    }

}
