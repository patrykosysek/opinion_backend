package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.list.watch.MangaWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaWatchListService extends BasicService<MangaWatchList, MangaWatchListRepository> {

    @Override
    public MangaWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsByWatchListAndManga(WatchList watchList, Manga manga) {
        return repository.existsByWatchListAndManga(watchList, manga);
    }

    public boolean existsByWatchListAndMangaId(WatchList watchList, UUID mangaId) {
        return repository.existsByWatchListAndManga_Id(watchList, mangaId);
    }

    public MangaWatchList findByMangaIdAndWatchList(UUID mangaId, WatchList watchList) {
        return repository.findByManga_IdAndWatchList(mangaId, watchList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
