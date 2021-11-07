package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieDiscussionRepository extends BasicRepository<MovieDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<MovieDiscussion> findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieDiscussion> findAllByMovie_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MovieDiscussion> findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieDiscussion> findAllByMovieGenresAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime date);

}
