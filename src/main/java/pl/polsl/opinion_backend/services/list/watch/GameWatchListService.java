package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.list.watch.GameWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_WATCH_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameWatchListService extends BasicService<GameWatchList, GameWatchListRepository> {

    @Override
    public GameWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_WATCH_LIST_NOT_FOUND));
    }

    public boolean existsByWatchListAndGame(WatchList watchList, Game game) {
        return repository.existsByWatchListAndGame(watchList, game);
    }

    public boolean existsByWatchListAndGameId(WatchList watchList, UUID gameId) {
        return repository.existsByWatchListAndGame_Id(watchList, gameId);
    }

    public GameWatchList findByGameIdAndWatchList(UUID gameId, WatchList watchList) {
        return repository.findByGame_IdAndWatchList(gameId, watchList).orElseThrow(() -> new IllegalArgumentException(GAME_WATCH_LIST_NOT_FOUND));
    }

    public Set<GameWatchList> findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<GameWatchList> findAllByGameIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByGame_IdAndCreateDateIsBefore(id, date);
    }

    public Set<GameWatchList> findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(gameGenre, startDate, endDate);
    }

    public Set<GameWatchList> findAllByGameGenresAndCreateDateIsBefore(String gameGenre, OffsetDateTime date) {
        return repository.findAllByGameGenresNameAndCreateDateIsBefore(gameGenre, date);
    }

    public Set<GameWatchList> findAllByGenresName(String genre) {
        return repository.findAllByGameGenresName(genre);
    }

}
