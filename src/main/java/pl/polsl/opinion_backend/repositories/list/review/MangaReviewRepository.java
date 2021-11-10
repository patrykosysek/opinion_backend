package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaReviewRepository extends BasicRepository<MangaReview, UUID> {

    boolean existsByReviewListAndManga_Id(ReviewList reviewList, UUID mangaId);

    Optional<MangaReview> findByManga_IdAndReviewList(UUID mangaId, ReviewList reviewList);

    Set<MangaReview> findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaReview> findAllByManga_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MangaReview> findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaReview> findAllByMangaGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<MangaReview> findAllByMangaGenresName(String name);

    Page<MangaReview> findAllByReviewList(ReviewList reviewList, Pageable pageable);

}
