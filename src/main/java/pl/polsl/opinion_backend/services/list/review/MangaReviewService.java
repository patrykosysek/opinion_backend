package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.repositories.list.review.MangaReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaReviewService extends BasicService<MangaReview, MangaReviewRepository> {

    @Override
    public MangaReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_REVIEW_NOT_FOUND));
    }

    public boolean existsByReviewListAndMangaId(ReviewList reviewList, UUID mangaId) {
        return repository.existsByReviewListAndManga_Id(reviewList, mangaId);
    }

    public MangaReview findByMangaIdAndReviewList(UUID mangaId, ReviewList reviewList) {
        return repository.findByManga_IdAndReviewList(mangaId, reviewList).orElseThrow(() -> new IllegalArgumentException(MANGA_REVIEW_NOT_FOUND));
    }

    public Set<MangaReview> findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MangaReview> findAllByMangaIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByManga_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MangaReview> findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<MangaReview> findAllByMangaGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByMangaGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<MangaReview> findAllByGenresName(String genre) {
        return repository.findAllByMangaGenresName(genre);
    }

}
