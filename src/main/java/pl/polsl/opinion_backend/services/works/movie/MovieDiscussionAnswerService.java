package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.movie.MovieDiscussionAnswerRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_DISCUSSION_ANSWER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieDiscussionAnswerService extends BasicService<MovieDiscussionAnswer, MovieDiscussionAnswerRepository> {

    @Override
    public MovieDiscussionAnswer getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_DISCUSSION_ANSWER_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public List<MovieDiscussionAnswer> findAllByDiscussion(MovieDiscussion movieDiscussion) {
        return repository.findAllByDiscussion(movieDiscussion );
    }

}
