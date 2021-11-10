package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameReviewRepository extends BasicRepository<GameReview, UUID> {

    boolean existsByReviewListAndGame_Id(ReviewList reviewList, UUID gameId);

    Optional<GameReview> findByGame_IdAndReviewList(UUID gameId, ReviewList reviewList);

    Set<GameReview> findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameReview> findAllByGame_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<GameReview> findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameReview> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date);

    Set<GameReview> findAllByGameGenresName(String name);

    Page<GameReview> findAllByReviewList(ReviewList reviewList, Pageable pageable);


}
