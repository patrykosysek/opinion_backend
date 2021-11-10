package pl.polsl.opinion_backend.services.list.review.like;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReviewLike;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeLikesMapping;
import pl.polsl.opinion_backend.repositories.list.review.like.AnimeReviewLikeRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_REVIEW_LIKE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeReviewLikeService extends BasicService<AnimeReviewLike, AnimeReviewLikeRepository> {
    private final UserService userService;

    @Override
    public AnimeReviewLike getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_REVIEW_LIKE_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID id) {
        repository.deleteAllByCreateBy(id);
    }

    public boolean existsByAnimeReviewAndCreateBy(AnimeReview animeReview, UUID id) {
        return repository.existsByAnimeReviewAndCreateBy(animeReview, id);
    }

    public Set<AnimeReviewLike> findAllByPositive(boolean positive, AnimeReview animeReview) {
        return repository.findAllByPositiveAndAnimeReview(positive, animeReview);
    }

    @AnimeLikesMapping
    public int getLike(AnimeReview animeReview) {
        Set<AnimeReviewLike> negativeAnimeReviewLikes = findAllByPositive(false, animeReview);
        Set<AnimeReviewLike> positiveAnimeReviewLikes = findAllByPositive(true, animeReview);
        return positiveAnimeReviewLikes.size() - negativeAnimeReviewLikes.size();
    }

    @AnimeIsLikedMapping
    public Optional<Boolean> isLiked(AnimeReview animeReview) {
        UUID userId = userService.getCurrentUser().getId();

        Optional<AnimeReviewLike> animeReviewLike = repository.findByAnimeReviewAndCreateBy(animeReview, userId);
        return animeReviewLike.map(AnimeReviewLike::isPositive);
    }

    public AnimeReviewLike findByAnimeReviewAndCreateBy(AnimeReview animeReview, UUID id) {
        return repository.findByAnimeReviewAndCreateBy(animeReview, id).orElseThrow(() -> new IllegalArgumentException(ANIME_REVIEW_LIKE_NOT_FOUND));
    }

}

