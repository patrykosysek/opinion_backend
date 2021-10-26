package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.repositories.works.MovieRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieService extends WorkOfCultureService<Movie, MovieRepository> {

    @Override
    public Movie getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
