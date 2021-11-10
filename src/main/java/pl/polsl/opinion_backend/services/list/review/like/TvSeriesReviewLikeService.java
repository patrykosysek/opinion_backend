package pl.polsl.opinion_backend.services.list.review.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReviewLike;
import pl.polsl.opinion_backend.mappers.qualifires.TvSeriesIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.TvSeriesLikesMapping;
import pl.polsl.opinion_backend.repositories.list.review.like.TvSeriesReviewLikeRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_REVIEW_LIKE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesReviewLikeService extends BasicService<TvSeriesReviewLike, TvSeriesReviewLikeRepository> {
    private final UserService userService;

    @Override
    public TvSeriesReviewLike getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_REVIEW_LIKE_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID id) {
        repository.deleteAllByCreateBy(id);
    }

    public boolean existsByTvSeriesReviewAndCreateBy(TvSeriesReview tvSeriesReview, UUID id) {
        return repository.existsByTvSeriesReviewAndCreateBy(tvSeriesReview, id);
    }

    public Set<TvSeriesReviewLike> findAllByPositive(boolean positive, TvSeriesReview tvSeriesReview) {
        return repository.findAllByPositiveAndTvSeriesReview(positive, tvSeriesReview);
    }

    @TvSeriesLikesMapping
    public int getLike(TvSeriesReview tvSeriesReview) {
        Set<TvSeriesReviewLike> negativeTvSeriesReviewLikes = findAllByPositive(false, tvSeriesReview);
        Set<TvSeriesReviewLike> positiveTvSeriesReviewLikes = findAllByPositive(true, tvSeriesReview);
        return positiveTvSeriesReviewLikes.size() - negativeTvSeriesReviewLikes.size();
    }

    @TvSeriesIsLikedMapping
    public Optional<Boolean> isLiked(TvSeriesReview tvSeriesReview) {
        UUID userId = userService.getCurrentUser().getId();

        Optional<TvSeriesReviewLike> tvSeriesReviewLike = repository.findByTvSeriesReviewAndCreateBy(tvSeriesReview, userId);
        return tvSeriesReviewLike.map(TvSeriesReviewLike::isPositive);
    }

    public TvSeriesReviewLike findByTvSeriesReviewAndCreateBy(TvSeriesReview tvSeriesReview, UUID id) {
        return repository.findByTvSeriesReviewAndCreateBy(tvSeriesReview, id).orElseThrow(() -> new IllegalArgumentException(TV_SERIES_REVIEW_LIKE_NOT_FOUND));
    }

}
