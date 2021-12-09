package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieStatistic;
import pl.polsl.opinion_backend.repositories.works.movie.MovieStatisticRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_STATISTIC_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieStatisticService extends BasicService<MovieStatistic, MovieStatisticRepository> {

    @Override
    public MovieStatistic getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_STATISTIC_NOT_FOUND));
    }

}
