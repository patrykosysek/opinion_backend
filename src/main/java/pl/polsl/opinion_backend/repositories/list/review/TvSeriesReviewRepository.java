package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
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

    Set<TvSeriesReview> findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesReview> findAllByTvSeriesGenresAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime date);

}
