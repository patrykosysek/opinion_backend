package pl.polsl.opinion_backend.repositories.list.review.like;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReviewLike;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TvSeriesReviewLikeRepository extends BasicRepository<TvSeriesReviewLike, UUID> {

    void deleteAllByCreateBy(UUID id);

    boolean existsByTvSeriesReviewAndCreateBy(TvSeriesReview tvSeriesReview, UUID id);

    Set<TvSeriesReviewLike> findAllByPositiveAndTvSeriesReview(boolean positive, TvSeriesReview tvSeriesReview);

    Optional<TvSeriesReviewLike> findByTvSeriesReviewAndCreateBy(TvSeriesReview tvSeriesReview, UUID id);

}
