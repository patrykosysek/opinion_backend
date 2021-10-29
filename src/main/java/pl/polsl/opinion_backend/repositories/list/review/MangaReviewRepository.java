package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaReviewRepository extends BasicRepository<MangaReview, UUID> {

    boolean existsByReviewListAndManga_Id(ReviewList reviewList, UUID mangaId);

    Optional<MangaReview> findByManga_IdAndReviewList(UUID mangaId, ReviewList reviewList);

}
