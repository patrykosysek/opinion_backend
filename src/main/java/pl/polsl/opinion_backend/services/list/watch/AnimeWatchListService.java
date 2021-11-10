package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.list.watch.AnimeWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_WATCH_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeWatchListService extends BasicService<AnimeWatchList, AnimeWatchListRepository> {

    @Override
    public AnimeWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_WATCH_LIST_NOT_FOUND));
    }

    public boolean existsByWatchListAndAnime(WatchList watchList, Anime anime) {
        return repository.existsByWatchListAndAnime(watchList, anime);
    }

    public boolean existsByWatchListAndAnimeId(WatchList watchList, UUID animeId) {
        return repository.existsByWatchListAndAnime_Id(watchList, animeId);
    }


    public AnimeWatchList findByAnimeIdAndWatchList(UUID animeId, WatchList watchList) {
        return repository.findByAnime_IdAndWatchList(animeId, watchList).orElseThrow(() -> new IllegalArgumentException(ANIME_WATCH_LIST_NOT_FOUND));
    }

    public Set<AnimeWatchList> findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<AnimeWatchList> findAllByAnimeIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByAnime_IdAndCreateDateIsBefore(id, date);
    }

    public Set<AnimeWatchList> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<AnimeWatchList> findAllByAnimeGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<AnimeWatchList> findAllByGenresName(String genre) {
        return repository.findAllByAnimeGenresName(genre);
    }

    public Page<AnimeWatchList> getAllByWatchList(WatchList watchList, Pageable pageable){
        return repository.findAllByWatchList(watchList,pageable);
    }

}
