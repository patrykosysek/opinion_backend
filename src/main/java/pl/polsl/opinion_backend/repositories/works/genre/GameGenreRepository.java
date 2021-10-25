package pl.polsl.opinion_backend.repositories.works.genre;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameGenreRepository extends BasicRepository<GameGenre, UUID> {

    Optional<GameGenre> findByName(String name);

}
