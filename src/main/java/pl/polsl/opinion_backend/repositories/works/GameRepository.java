package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;

import java.util.UUID;

@Repository
public interface GameRepository extends WorkOfCultureRepository<Game, UUID> {
}
