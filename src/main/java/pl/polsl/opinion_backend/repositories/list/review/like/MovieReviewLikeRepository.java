package pl.polsl.opinion_backend.repositories.list.review.like;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReviewLike;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieReviewLikeRepository extends BasicRepository<MovieReviewLike, UUID> {

    void deleteAllByCreateBy(UUID id);

    boolean existsByMovieReviewAndCreateBy(MovieReview movieReview, UUID id);

    Set<MovieReviewLike> findAllByPositiveAndMovieReview(boolean positive, MovieReview movieReview);

    Optional<MovieReviewLike> findByMovieReviewAndCreateBy(MovieReview movieReview, UUID id);

}
