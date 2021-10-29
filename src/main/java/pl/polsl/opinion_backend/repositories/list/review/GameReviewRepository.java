package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameReviewRepository extends BasicRepository<GameReview, UUID> {

    boolean existsByReviewListAndGame_Id(ReviewList reviewList, UUID gameId);

    Optional<GameReview> findByGame_IdAndReviewList(UUID gameId, ReviewList reviewList);

}
