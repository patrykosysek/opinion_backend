package pl.polsl.opinion_backend.repositories.works.game;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface GameDiscussionRepository extends BasicRepository<GameDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
