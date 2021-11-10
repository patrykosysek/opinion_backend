package pl.polsl.opinion_backend.repositories.list.review.like;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReviewLike;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReviewLike;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameReviewLikeRepository extends BasicRepository<GameReviewLike, UUID> {

    void deleteAllByCreateBy(UUID id);

    boolean existsByGameReviewAndCreateBy(GameReview gameReview, UUID id);

    Set<GameReviewLike> findAllByPositiveAndGameReview(boolean positive,GameReview gameReview);

    Optional<GameReviewLike> findByGameReviewAndCreateBy(GameReview gameReview, UUID id);

}
