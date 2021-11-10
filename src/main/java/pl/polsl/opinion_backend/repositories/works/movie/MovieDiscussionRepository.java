package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
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

    Set<MovieDiscussion> findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieDiscussion> findAllByMovieGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<MovieDiscussion> findAllByMovieGenresName(String name);

    Page<MovieDiscussion> findAllByCreateBy(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<MovieDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_Id(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_IdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<MovieDiscussion> findAllByMovie_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

}
