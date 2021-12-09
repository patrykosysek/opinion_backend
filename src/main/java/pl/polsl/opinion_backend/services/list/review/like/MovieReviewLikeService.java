package pl.polsl.opinion_backend.services.list.review.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReviewLike;
import pl.polsl.opinion_backend.mappers.qualifires.MovieIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.MovieLikesMapping;
import pl.polsl.opinion_backend.repositories.list.review.like.MovieReviewLikeRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_REVIEW_LIKE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieReviewLikeService extends BasicService<MovieReviewLike, MovieReviewLikeRepository> {
    private final UserService userService;

    @Override
    public MovieReviewLike getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_REVIEW_LIKE_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID id) {
        repository.deleteAllByCreateBy(id);
    }

    public boolean existsByMovieReviewAndCreateBy(MovieReview movieReview, UUID id) {
        return repository.existsByMovieReviewAndCreateBy(movieReview, id);
    }

    public Set<MovieReviewLike> findAllByPositive(boolean positive, MovieReview movieReview) {
        return repository.findAllByPositiveAndMovieReview(positive, movieReview);
    }

    @MovieLikesMapping
    public int getLike(MovieReview movieReview) {
        Set<MovieReviewLike> negativeMovieReviewLikes = findAllByPositive(false, movieReview);
        Set<MovieReviewLike> positiveMovieReviewLikes = findAllByPositive(true, movieReview);
        return positiveMovieReviewLikes.size() - negativeMovieReviewLikes.size();
    }

    @MovieIsLikedMapping
    public Optional<Boolean> isLiked(MovieReview movieReview) {
        UUID userId = userService.getCurrentUser().getId();

        Optional<MovieReviewLike> movieReviewLike = repository.findByMovieReviewAndCreateBy(movieReview, userId);
        return movieReviewLike.map(MovieReviewLike::isPositive);
    }

    public MovieReviewLike findByMovieReviewAndCreateBy(MovieReview movieReview, UUID id) {
        return repository.findByMovieReviewAndCreateBy(movieReview, id).orElseThrow(() -> new IllegalArgumentException(MOVIE_REVIEW_LIKE_NOT_FOUND));
    }

}
