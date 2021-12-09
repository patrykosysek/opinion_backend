package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.movie.MovieDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieDiscussionService extends BasicService<MovieDiscussion, MovieDiscussionRepository> {
    private final MovieService movieService;
    private final MovieDiscussionAnswerService movieDiscussionAnswerService;

    @Override
    public MovieDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_DISCUSSION_NOT_FOUND));
    }

    public MovieDiscussion addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Movie movie = movieService.getById(workOfCultureId);
        MovieDiscussion movieDiscussion = new MovieDiscussion();
        movieDiscussion.addMovie(movie);
        movieDiscussion.setText(dto.getText());
        movieDiscussion.setTopic(dto.getTopic());
        movie.getStatistic().setCurrentDiscussion(movie.getStatistic().getCurrentDiscussion() + 1);
        movieService.save(movie);
        return save(movieDiscussion);
    }

    public MovieDiscussionAnswer addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        MovieDiscussion movieDiscussion = getById(discussionId);
        MovieDiscussionAnswer movieDiscussionAnswer = new MovieDiscussionAnswer();
        movieDiscussionAnswer.setText(dto.getText());
        movieDiscussion.addAnswer(movieDiscussionAnswer);
        Movie movie = movieDiscussion.getMovie();
        movie.getStatistic().setCurrentDiscussion(movie.getStatistic().getCurrentDiscussion() + 1);
        movieService.save(movie);
        return movieDiscussionAnswerService.save(movieDiscussionAnswer);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<MovieDiscussion> findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MovieDiscussion> findAllByMovieIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByMovie_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MovieDiscussion> findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<MovieDiscussion> findAllByMovieGenresAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByMovieGenresNameAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

    public Set<MovieDiscussion> findAllByGenresName(String genre) {
        return repository.findAllByMovieGenresName(genre);
    }

    public Page<MovieDiscussion> findAllByCreateBy(UUID id, Pageable pageable) {
        return repository.findAllByCreateBy(id, pageable);
    }

    public Page<MovieDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateAsc(id, pageable);
    }

    public Page<MovieDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateDesc(id, pageable);
    }

    public Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieId(UUID id, Pageable pageable) {
        return repository.findAllByMovie_Id(id, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieIdOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByMovie_IdOrderByCreateDateAsc(id, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieIdOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByMovie_IdOrderByCreateDateDesc(id, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieIdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByMovie_IdAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByMovie_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<MovieDiscussion> findAllByMovieIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByMovie_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

}
