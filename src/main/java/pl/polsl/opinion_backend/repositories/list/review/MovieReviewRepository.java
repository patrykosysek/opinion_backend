package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieReviewRepository extends BasicRepository<MovieReview, UUID> {

    boolean existsByReviewListAndMovie_Id(ReviewList reviewList, UUID movieId);

    Optional<MovieReview> findByMovie_IdAndReviewList(UUID movieId, ReviewList reviewList);

    Set<MovieReview> findAllByMovie_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieReview> findAllByMovie_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MovieReview> findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieReview> findAllByMovieGenresAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime date);

}
