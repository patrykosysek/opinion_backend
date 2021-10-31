package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.repositories.list.review.MovieReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_REVIEW_NOT_FOUND;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieReviewService extends BasicService<MovieReview, MovieReviewRepository> {

    @Override
    public MovieReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_REVIEW_NOT_FOUND));
    }

    public boolean existsByReviewListAndMovieId(ReviewList reviewList, UUID movieId) {
        return repository.existsByReviewListAndMovie_Id(reviewList, movieId);
    }

    public MovieReview findByMovieIdAndReviewList(UUID movieId, ReviewList reviewList) {
        return repository.findByMovie_IdAndReviewList(movieId, reviewList).orElseThrow(() -> new IllegalArgumentException(MOVIE_REVIEW_NOT_FOUND));
    }

}
