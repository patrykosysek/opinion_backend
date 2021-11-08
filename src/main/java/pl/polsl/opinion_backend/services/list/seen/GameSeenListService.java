package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.repositories.list.seen.GameSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_SEEN_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameSeenListService extends BasicService<GameSeenList, GameSeenListRepository> {

    @Override
    public GameSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_SEEN_LIST_NOT_FOUND));
    }

    public boolean existsBySeenListAndGame(SeenList seenList, Game game) {
        return repository.existsBySeenListAndGame(seenList, game);
    }

    public GameSeenList findByGameIdAndSeenList(UUID gameId, SeenList seenList) {
        return repository.findByGame_IdAndSeenList(gameId, seenList).orElseThrow(() -> new IllegalArgumentException(GAME_SEEN_LIST_NOT_FOUND));
    }


    public Set<GameSeenList> findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<GameSeenList> findAllByGameIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByGame_IdAndCreateDateIsBefore(id, date);
    }

    public Set<GameSeenList> findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(gameGenre, startDate, endDate);
    }

    public Set<GameSeenList> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date) {
        return repository.findAllByGameGenresNameAndCreateDateIsBefore(gameGenre, date);
    }

    public Set<GameSeenList> findAllByGenresName(String genre) {
        return repository.findAllByGameGenresName(genre);
    }

}
