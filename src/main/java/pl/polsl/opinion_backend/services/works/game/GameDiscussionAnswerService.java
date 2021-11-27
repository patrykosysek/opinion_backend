package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.game.GameDiscussionAnswerRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_DISCUSSION_ANSWER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameDiscussionAnswerService extends BasicService<GameDiscussionAnswer, GameDiscussionAnswerRepository> {

    @Override
    public GameDiscussionAnswer getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_DISCUSSION_ANSWER_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public List<GameDiscussionAnswer> findAllByDiscussion(GameDiscussion gameDiscussion) {
        return repository.findAllByDiscussionOrderByCreateDateDesc(gameDiscussion);
    }

}
