package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.repositories.list.review.AnimeReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeReviewService extends BasicService<AnimeReview, AnimeReviewRepository> {

    @Override
    public AnimeReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_REVIEW_NOT_FOUND));
    }

    public boolean existsByReviewListAndAnimeId(ReviewList reviewList, UUID animeId) {
        return repository.existsByReviewListAndAnime_Id(reviewList, animeId);
    }

    public AnimeReview findByAnimeIdAndReviewList(UUID animeId, ReviewList reviewList) {
        return repository.findByAnime_IdAndReviewList(animeId, reviewList).orElseThrow(() -> new IllegalArgumentException(ANIME_REVIEW_NOT_FOUND));
    }

    public Set<AnimeReview> findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<AnimeReview> findAllByAnimeIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByAnime_IdAndCreateDateIsBefore(id, date);
    }

    public Set<AnimeReview> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<AnimeReview> findAllByAnimeGenresAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByAnimeGenresAndCreateDateIsBefore(animeMangaGenre, date);
    }


}
