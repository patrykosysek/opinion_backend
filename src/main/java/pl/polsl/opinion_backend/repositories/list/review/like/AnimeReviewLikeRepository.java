package pl.polsl.opinion_backend.repositories.list.review.like;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReviewLike;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimeReviewLikeRepository extends BasicRepository<AnimeReviewLike, UUID> {

    void deleteAllByCreateBy(UUID id);

    boolean existsByAnimeReviewAndCreateBy(AnimeReview animeReview, UUID id);

    Set<AnimeReviewLike> findAllByPositiveAndAnimeReview(boolean positive, AnimeReview animeReview);

    Optional<AnimeReviewLike> findByAnimeReviewAndCreateBy(AnimeReview animeReview, UUID id);

}
