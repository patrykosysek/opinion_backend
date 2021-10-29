package pl.polsl.opinion_backend.services.list.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.repositories.list.review.MangaReviewRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaReviewService extends BasicService<MangaReview, MangaReviewRepository> {

    @Override
    public MangaReview getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsByReviewListAndMangaId(ReviewList reviewList, UUID mangaId) {
        return repository.existsByReviewListAndManga_Id(reviewList, mangaId);
    }

    public MangaReview findByMangaIdAndReviewList(UUID mangaId, ReviewList reviewList) {
        return repository.findByManga_IdAndReviewList(mangaId, reviewList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
