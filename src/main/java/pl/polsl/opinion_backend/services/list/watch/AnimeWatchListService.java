package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.list.watch.AnimeWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeWatchListService extends BasicService<AnimeWatchList, AnimeWatchListRepository> {

    @Override
    public AnimeWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsByWatchListAndAnime(WatchList watchList, Anime anime) {
        return repository.existsByWatchListAndAnime(watchList, anime);
    }

    public boolean existsByWatchListAndAnimeId(WatchList watchList, UUID animeId) {
        return repository.existsByWatchListAndAnime_Id(watchList, animeId);
    }


    public AnimeWatchList findByAnimeIdAndWatchList(UUID animeId, WatchList watchList) {
        return repository.findByAnime_IdAndWatchList(animeId, watchList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
