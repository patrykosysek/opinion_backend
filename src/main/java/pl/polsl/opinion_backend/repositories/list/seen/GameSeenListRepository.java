package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameSeenListRepository extends BasicRepository<GameSeenList, UUID> {

    boolean existsBySeenListAndGame(SeenList seenList, Game game);

    Optional<GameSeenList> findByGame_IdAndSeenList(UUID gameId, SeenList seenList);

    Set<GameSeenList> findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameSeenList> findAllByGame_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<GameSeenList> findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameSeenList> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date);

    Set<GameSeenList> findAllByGameGenresName(String name);

}
