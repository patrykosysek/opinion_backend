package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.repositories.list.review.AnimeReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_REVIEW_NOT_FOUND;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

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

}
