package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.list.watch.MangaWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_WATCH_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaWatchListService extends BasicService<MangaWatchList, MangaWatchListRepository> {

    @Override
    public MangaWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_WATCH_LIST_NOT_FOUND));
    }

    public boolean existsByWatchListAndManga(WatchList watchList, Manga manga) {
        return repository.existsByWatchListAndManga(watchList, manga);
    }

    public boolean existsByWatchListAndMangaId(WatchList watchList, UUID mangaId) {
        return repository.existsByWatchListAndManga_Id(watchList, mangaId);
    }

    public MangaWatchList findByMangaIdAndWatchList(UUID mangaId, WatchList watchList) {
        return repository.findByManga_IdAndWatchList(mangaId, watchList).orElseThrow(() -> new IllegalArgumentException(MANGA_WATCH_LIST_NOT_FOUND));
    }

    public Set<MangaWatchList> findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MangaWatchList> findAllByMangaIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByManga_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MangaWatchList> findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<MangaWatchList> findAllByMangaGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByMangaGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<MangaWatchList> findAllByGenresName(String genre) {
        return repository.findAllByMangaGenresName(genre);
    }

    public Page<MangaWatchList> getAllByWatchList(WatchList watchList, Pageable pageable){
        return repository.findAllByWatchList(watchList,pageable);
    }

}
