package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieTvSeriesGenreRepository extends BasicRepository<MovieTvSeriesGenre, UUID> {

    Optional<MovieTvSeriesGenre> findByName(String name);

}
