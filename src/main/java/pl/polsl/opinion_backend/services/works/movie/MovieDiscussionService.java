package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
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

    @Override
    public MovieDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_DISCUSSION_NOT_FOUND));
    }

    public void addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Movie movie = movieService.getById(workOfCultureId);
        MovieDiscussion movieDiscussion = new MovieDiscussion();
        movieDiscussion.addMovie(movie);
        movieDiscussion.setText(dto.getText());
        movieDiscussion.setTopic(dto.getTopic());
        movie.getStatistic().setCurrentDiscussion(movie.getStatistic().getCurrentDiscussion() + 1);
        movieService.save(movie);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        MovieDiscussion movieDiscussion = getById(discussionId);
        MovieDiscussionAnswer movieDiscussionAnswer = new MovieDiscussionAnswer();
        movieDiscussionAnswer.setText(dto.getText());
        movieDiscussion.addAnswer(movieDiscussionAnswer);
        Movie movie = movieDiscussion.getMovie();
        movie.getStatistic().setCurrentDiscussion(movie.getStatistic().getCurrentDiscussion() + 1);
        movieService.save(movie);
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

}
