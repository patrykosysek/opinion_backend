package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
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

    Set<MovieReview> findAllByMovieGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MovieReview> findAllByMovieGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<MovieReview> findAllByMovieGenresName(String name);

    Page<MovieReview> findAllByReviewList(ReviewList reviewList, Pageable pageable);

    Page<MovieReview> findAllByMovie_Id(UUID id, Pageable pageable);

    Page<MovieReview> findAllByMovie_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<MovieReview> findAllByMovie_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

}
