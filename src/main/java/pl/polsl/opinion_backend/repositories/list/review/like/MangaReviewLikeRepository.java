package pl.polsl.opinion_backend.repositories.list.review.like;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReviewLike;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaReviewLikeRepository extends BasicRepository<MangaReviewLike, UUID> {

    void deleteAllByCreateBy(UUID id);

    boolean existsByMangaReviewAndCreateBy(MangaReview mangaReview, UUID id);

    Set<MangaReviewLike> findAllByPositiveAndMangaReview(boolean positive, MangaReview mangaReview);

    Optional<MangaReviewLike> findByMangaReviewAndCreateBy(MangaReview mangaReview, UUID id);

}
