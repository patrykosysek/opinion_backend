package pl.polsl.opinion_backend.repositories.works.tvSeries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TvSeriesDiscussionRepository extends BasicRepository<TvSeriesDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<TvSeriesDiscussion> findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesDiscussion> findAllByTvSeries_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<TvSeriesDiscussion> findAllByTvSeriesGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesDiscussion> findAllByTvSeriesGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<TvSeriesDiscussion> findAllByTvSeriesGenresName(String name);

    Page<TvSeriesDiscussion> findAllByCreateBy(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_Id(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_IdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<TvSeriesDiscussion> findAllByTvSeries_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

}
