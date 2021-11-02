package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MangaDiscussionRepository extends BasicRepository<MangaDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
