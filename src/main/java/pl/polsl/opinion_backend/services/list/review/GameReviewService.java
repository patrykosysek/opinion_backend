package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.repositories.list.review.GameReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameReviewService extends BasicService<GameReview, GameReviewRepository> {

    @Override
    public GameReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_REVIEW_NOT_FOUND));
    }

    public boolean existsByReviewListAndGameId(ReviewList reviewList, UUID gameId) {
        return repository.existsByReviewListAndGame_Id(reviewList, gameId);
    }

    public GameReview existsByReviewListAndGameId(UUID gameId, ReviewList reviewList) {
        return repository.findByGame_IdAndReviewList(gameId, reviewList).orElseThrow(() -> new IllegalArgumentException(GAME_REVIEW_NOT_FOUND));
    }

    public Set<GameReview> findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<GameReview> findAllByGameIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByGame_IdAndCreateDateIsBefore(id, date);
    }

    public Set<GameReview> findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(gameGenre, startDate, endDate);
    }

    public Set<GameReview> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date) {
        return repository.findAllByGameGenresNameAndCreateDateIsBefore(gameGenre, date);
    }

    public Set<GameReview> findAllByGenresName(String genre) {
        return repository.findAllByGameGenresName(genre);
    }

}
