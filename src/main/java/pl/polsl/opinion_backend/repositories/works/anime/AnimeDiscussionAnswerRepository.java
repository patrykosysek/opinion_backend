package pl.polsl.opinion_backend.repositories.works.anime;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimeDiscussionAnswerRepository extends BasicRepository<AnimeDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    List<AnimeDiscussionAnswer> findAllByDiscussionOrderByCreateDateDesc(AnimeDiscussion animeDiscussion);

}
