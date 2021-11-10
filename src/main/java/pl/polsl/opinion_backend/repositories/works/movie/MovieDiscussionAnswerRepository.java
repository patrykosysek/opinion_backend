package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MovieDiscussionAnswerRepository extends BasicRepository<MovieDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Page<MovieDiscussionAnswer> findAllByDiscussion(MovieDiscussion movieDiscussion, Pageable pageable);

}
