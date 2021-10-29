package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameSeenListRepository extends BasicRepository<GameSeenList, UUID> {

    boolean existsBySeenListAndGame(SeenList seenList, Game game);

    Optional<GameSeenList> findByGame_IdAndSeenList(UUID gameId, SeenList seenList);

}
