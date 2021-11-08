package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameWatchListRepository extends BasicRepository<GameWatchList, UUID> {

    boolean existsByWatchListAndGame(WatchList watchList, Game game);

    boolean existsByWatchListAndGame_Id(WatchList watchList, UUID gameId);

    Optional<GameWatchList> findByGame_IdAndWatchList(UUID gameId, WatchList watchList);

    Set<GameWatchList> findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameWatchList> findAllByGame_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<GameWatchList> findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameWatchList> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date);

    Set<GameWatchList> findAllByGameGenresName(String name);

}
