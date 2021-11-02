package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MovieDiscussionAnswerRepository extends BasicRepository<MovieDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

}
