package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.repositories.list.review.GameReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameReviewService extends BasicService<GameReview, GameReviewRepository> {

    @Override
    public GameReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsByReviewListAndGameId(ReviewList reviewList, UUID gameId) {
        return repository.existsByReviewListAndGame_Id(reviewList, gameId);
    }

    public GameReview existsByReviewListAndGameId(UUID gameId, ReviewList reviewList) {
        return repository.findByGame_IdAndReviewList(gameId, reviewList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
