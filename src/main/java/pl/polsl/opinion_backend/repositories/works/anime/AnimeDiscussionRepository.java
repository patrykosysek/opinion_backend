package pl.polsl.opinion_backend.repositories.works.anime;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface AnimeDiscussionRepository extends BasicRepository<AnimeDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
