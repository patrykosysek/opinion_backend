package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameWatchListRepository extends BasicRepository<GameWatchList, UUID> {

    boolean existsByWatchListAndGame(WatchList watchList, Game game);

    boolean existsByWatchListAndGame_Id(WatchList watchList, UUID gameId);

    Optional<GameWatchList> findByGame_IdAndWatchList(UUID gameId, WatchList watchList);

}
