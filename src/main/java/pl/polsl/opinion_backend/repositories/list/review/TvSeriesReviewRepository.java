package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TvSeriesReviewRepository extends BasicRepository<TvSeriesReview, UUID> {

    boolean existsByReviewListAndTvSeries_Id(ReviewList reviewList, UUID tvSeriesId);

    Optional<TvSeriesReview> findByTvSeries_IdAndReviewList(UUID tvSeriesId, ReviewList reviewList);

    Set<TvSeriesReview> findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesReview> findAllByTvSeries_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<TvSeriesReview> findAllByTvSeriesGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesReview> findAllByTvSeriesGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<TvSeriesReview> findAllByTvSeriesGenresName(String name);

    Page<TvSeriesReview> findAllByReviewList(ReviewList reviewList, Pageable pageable);

    Page<TvSeriesReview> findAllByTvSeries_Id(UUID id, Pageable pageable);

    Page<TvSeriesReview> findAllByTvSeries_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<TvSeriesReview> findAllByTvSeries_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

}
