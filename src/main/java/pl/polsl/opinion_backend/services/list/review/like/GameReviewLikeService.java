package pl.polsl.opinion_backend.services.list.review.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReviewLike;
import pl.polsl.opinion_backend.mappers.qualifires.GameIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.GameLikesMapping;
import pl.polsl.opinion_backend.repositories.list.review.like.GameReviewLikeRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_REVIEW_LIKE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameReviewLikeService extends BasicService<GameReviewLike, GameReviewLikeRepository> {
    private final UserService userService;

    @Override
    public GameReviewLike getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_REVIEW_LIKE_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID id) {
        repository.deleteAllByCreateBy(id);
    }

    public boolean existsByGameReviewAndCreateBy(GameReview gameReview, UUID id) {
        return repository.existsByGameReviewAndCreateBy(gameReview, id);
    }

    public Set<GameReviewLike> findAllByPositive(boolean positive, GameReview gameReview) {
        return repository.findAllByPositiveAndGameReview(positive, gameReview);
    }

    @GameLikesMapping
    public int getLike(GameReview gameReview) {
        Set<GameReviewLike> negativeGameReviewLikes = findAllByPositive(false, gameReview);
        Set<GameReviewLike> positiveGameReviewLikes = findAllByPositive(true, gameReview);
        return positiveGameReviewLikes.size() - negativeGameReviewLikes.size();
    }

    @GameIsLikedMapping
    public Optional<Boolean> isLiked(GameReview gameReview) {
        UUID userId = userService.getCurrentUser().getId();

        Optional<GameReviewLike> gameReviewLike = repository.findByGameReviewAndCreateBy(gameReview, userId);
        return gameReviewLike.map(GameReviewLike::isPositive);
    }

    public GameReviewLike findByGameReviewAndCreateBy(GameReview gameReview, UUID id) {
        return repository.findByGameReviewAndCreateBy(gameReview, id).orElseThrow(() -> new IllegalArgumentException(GAME_REVIEW_LIKE_NOT_FOUND));
    }

}
