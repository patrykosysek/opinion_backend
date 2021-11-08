package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.repositories.list.seen.MangaSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_SEEN_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaSeenListService extends BasicService<MangaSeenList, MangaSeenListRepository> {

    @Override
    public MangaSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_SEEN_LIST_NOT_FOUND));
    }

    public boolean existsBySeenListAndManga(SeenList seenList, Manga manga) {
        return repository.existsBySeenListAndManga(seenList, manga);
    }

    public MangaSeenList findByMangaIdAndSeenList(UUID mangaId, SeenList seenList) {
        return repository.findByManga_IdAndSeenList(mangaId, seenList).orElseThrow(() -> new IllegalArgumentException(MANGA_SEEN_LIST_NOT_FOUND));
    }

    public Set<MangaSeenList> findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MangaSeenList> findAllByMangaIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByManga_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MangaSeenList> findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<MangaSeenList> findAllByMangaGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByMangaGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<MangaSeenList> findAllByGenresName(String genre) {
        return repository.findAllByMangaGenresName(genre);
    }

}
