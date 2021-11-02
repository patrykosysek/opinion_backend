package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MangaDiscussionAnswerRepository extends BasicRepository<MangaDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
