package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MangaDiscussionAnswerRepository extends BasicRepository<MangaDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Page<MangaDiscussionAnswer> findAllByDiscussion(MangaDiscussion mangaDiscussion, Pageable pageable);

}
