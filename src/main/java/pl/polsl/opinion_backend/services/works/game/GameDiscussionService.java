package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.game.GameDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameDiscussionService extends BasicService<GameDiscussion, GameDiscussionRepository> {
    private final GameService gameService;

    @Override
    public GameDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_DISCUSSION_NOT_FOUND));
    }

    public void addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Game game = gameService.getById(workOfCultureId);
        GameDiscussion gameDiscussion = new GameDiscussion();
        gameDiscussion.addGame(game);
        gameDiscussion.setText(dto.getText());
        gameDiscussion.setTopic(dto.getTopic());
        game.getStatistic().setCurrentDiscussion(game.getStatistic().getCurrentDiscussion() + 1);
        gameService.save(game);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        GameDiscussion gameDiscussion = getById(discussionId);
        GameDiscussionAnswer gameDiscussionAnswer = new GameDiscussionAnswer();
        gameDiscussionAnswer.setText(dto.getText());
        gameDiscussion.addAnswer(gameDiscussionAnswer);
        Game game = gameDiscussion.getGame();
        game.getStatistic().setCurrentDiscussion(game.getStatistic().getCurrentDiscussion() + 1);
        gameService.save(game);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<GameDiscussion> findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public  Set<GameDiscussion> findAllByGameIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByGame_IdAndCreateDateIsBefore(id, date);
    }

    public Set<GameDiscussion> findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(GameGenre gameGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(gameGenre, startDate, endDate);
    }

    public  Set<GameDiscussion> findAllByGameGenresAndCreateDateIsBefore(GameGenre gameGenre, OffsetDateTime date) {
        return repository.findAllByGameGenresAndCreateDateIsBefore(gameGenre, date);
    }

}
