package pl.polsl.opinion_backend.services.list.review.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReviewLike;
import pl.polsl.opinion_backend.mappers.qualifires.MangaIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.MangaLikesMapping;
import pl.polsl.opinion_backend.repositories.list.review.like.MangaReviewLikeRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_REVIEW_LIKE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaReviewLikeService extends BasicService<MangaReviewLike, MangaReviewLikeRepository> {
    private final UserService userService;

    @Override
    public MangaReviewLike getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_REVIEW_LIKE_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID id) {
        repository.deleteAllByCreateBy(id);
    }

    public boolean existsByMangaReviewAndCreateBy(MangaReview mangaReview, UUID id) {
        return repository.existsByMangaReviewAndCreateBy(mangaReview, id);
    }

    public Set<MangaReviewLike> findAllByPositive(boolean positive, MangaReview mangaReview) {
        return repository.findAllByPositiveAndMangaReview(positive, mangaReview);
    }

    @MangaLikesMapping
    public int getLike(MangaReview mangaReview) {
        Set<MangaReviewLike> negativeMangaReviewLikes = findAllByPositive(false, mangaReview);
        Set<MangaReviewLike> positiveMangaReviewLikes = findAllByPositive(true, mangaReview);
        return positiveMangaReviewLikes.size() - negativeMangaReviewLikes.size();
    }

    @MangaIsLikedMapping
    public Optional<Boolean> isLiked(MangaReview mangaReview) {
        UUID userId = userService.getCurrentUser().getId();

        Optional<MangaReviewLike> mangaReviewLike = repository.findByMangaReviewAndCreateBy(mangaReview, userId);
        return mangaReviewLike.map(MangaReviewLike::isPositive);
    }

    public MangaReviewLike findByMangaReviewAndCreateBy(MangaReview mangaReview, UUID id) {
        return repository.findByMangaReviewAndCreateBy(mangaReview, id).orElseThrow(() -> new IllegalArgumentException(MANGA_REVIEW_LIKE_NOT_FOUND));
    }


}
