package pl.polsl.opinion_backend.repositories.works.game;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface GameDiscussionAnswerRepository extends BasicRepository<GameDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
