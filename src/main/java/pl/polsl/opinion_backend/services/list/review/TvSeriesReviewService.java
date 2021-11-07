package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.repositories.list.review.TvSeriesReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesReviewService extends BasicService<TvSeriesReview, TvSeriesReviewRepository> {

    @Override
    public TvSeriesReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_REVIEW_NOT_FOUND));
    }

    public boolean existsByReviewListAndTvSeriesId(ReviewList reviewList, UUID tvSeriesId) {
        return repository.existsByReviewListAndTvSeries_Id(reviewList, tvSeriesId);
    }

    public TvSeriesReview findByTvSeriesIdAndReviewList(UUID tvSeriesId, ReviewList reviewList) {
        return repository.findByTvSeries_IdAndReviewList(tvSeriesId, reviewList).orElseThrow(() -> new IllegalArgumentException(TV_SERIES_REVIEW_NOT_FOUND));
    }

    public Set<TvSeriesReview> findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<TvSeriesReview> findAllByTvSeries_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByTvSeries_IdAndCreateDateIsBefore(id, date);
    }

    public Set<TvSeriesReview> findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<TvSeriesReview> findAllByTvSeriesGenresAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByTvSeriesGenresAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

}
