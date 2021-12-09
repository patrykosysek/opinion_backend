package pl.polsl.opinion_backend.repositories.works.game;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.works.WorkOfCultureRepository;

import java.util.UUID;

@Repository
public interface GameRepository extends WorkOfCultureRepository<Game, UUID> {
}
