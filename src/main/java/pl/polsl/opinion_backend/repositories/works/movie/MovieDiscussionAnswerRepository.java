package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieDiscussionAnswerRepository extends BasicRepository<MovieDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    List<MovieDiscussionAnswer> findAllByDiscussion(MovieDiscussion movieDiscussion);

}
