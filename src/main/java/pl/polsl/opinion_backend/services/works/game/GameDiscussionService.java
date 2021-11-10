package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionCreateDTO;
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
    private final GameDiscussionAnswerService gameDiscussionAnswerService;

    @Override
    public GameDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_DISCUSSION_NOT_FOUND));
    }

    public GameDiscussion addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Game game = gameService.getById(workOfCultureId);
        GameDiscussion gameDiscussion = new GameDiscussion();
        gameDiscussion.addGame(game);
        gameDiscussion.setText(dto.getText());
        gameDiscussion.setTopic(dto.getTopic());
        game.getStatistic().setCurrentDiscussion(game.getStatistic().getCurrentDiscussion() + 1);
        gameService.save(game);
        return save(gameDiscussion);
    }

    public GameDiscussionAnswer addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        GameDiscussion gameDiscussion = getById(discussionId);
        GameDiscussionAnswer gameDiscussionAnswer = new GameDiscussionAnswer();
        gameDiscussionAnswer.setText(dto.getText());
        gameDiscussion.addAnswer(gameDiscussionAnswer);
        Game game = gameDiscussion.getGame();
        game.getStatistic().setCurrentDiscussion(game.getStatistic().getCurrentDiscussion() + 1);
        gameService.save(game);
        return gameDiscussionAnswerService.save(gameDiscussionAnswer);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<GameDiscussion> findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<GameDiscussion> findAllByGameIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByGame_IdAndCreateDateIsBefore(id, date);
    }

    public Set<GameDiscussion> findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(gameGenre, startDate, endDate);
    }

    public Set<GameDiscussion> findAllByGameGenresAndCreateDateIsBefore(String gameGenre, OffsetDateTime date) {
        return repository.findAllByGameGenresNameAndCreateDateIsBefore(gameGenre, date);
    }

    public Set<GameDiscussion> findAllByGenresName(String genre) {
        return repository.findAllByGameGenresName(genre);
    }

    public Page<GameDiscussion> findAllByCreateBy(UUID id, Pageable pageable) {
        return repository.findAllByCreateBy(id, pageable);
    }

    public Page<GameDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateAsc(id, pageable);
    }

    public Page<GameDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateDesc(id, pageable);
    }

    public Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

    public Page<GameDiscussion> findAllByGameId(UUID id, Pageable pageable) {
        return repository.findAllByGame_Id(id, pageable);
    }

    public Page<GameDiscussion> findAllByGameIdOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByGame_IdOrderByCreateDateAsc(id, pageable);
    }

    public Page<GameDiscussion> findAllByGameIdOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByGame_IdOrderByCreateDateDesc(id, pageable);
    }

    public Page<GameDiscussion> findAllByGameIdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByGame_IdAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<GameDiscussion> findAllByGameIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByGame_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<GameDiscussion> findAllByGameIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByGame_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }


}
