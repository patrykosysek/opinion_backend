package pl.polsl.opinion_backend.repositories.list.review;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimeReviewRepository extends BasicRepository<AnimeReview, UUID> {

    boolean existsByReviewListAndAnime_Id(ReviewList reviewList, UUID animeId);

    Optional<AnimeReview> findByAnime_IdAndReviewList(UUID animeId, ReviewList reviewList);

    Set<AnimeReview> findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeReview> findAllByAnime_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<AnimeReview> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeReview> findAllByAnimeGenresAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime date);

}
