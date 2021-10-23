package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.repositories.works.MovieTvSeriesGenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieTvSeriesGenreService extends BasicService<MovieTvSeriesGenre, MovieTvSeriesGenreRepository> {

    @Override
    public MovieTvSeriesGenre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    public MovieTvSeriesGenre getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
