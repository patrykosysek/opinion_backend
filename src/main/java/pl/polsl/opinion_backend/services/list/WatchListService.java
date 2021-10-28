package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.list.WatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.*;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class WatchListService extends BasicService<WatchList, WatchListRepository> {
    private final UserService userService;
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;

    @Override
    public WatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public WatchList getCurrentUserWatchList() {
        return userService.getCurrentUser().getWatchList();
    }

    public void addWorkOfCulture(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        WatchList watchList = getCurrentUserWatchList();

        switch (workOfCultureType) {
            case ANIME:
                addAnime(workOfCultureId, watchList);
                break;
            case MANGA:
                addManga(workOfCultureId, watchList);
                break;
            case MOVIE:
                addMovie(workOfCultureId, watchList);
                break;
            case TVSERIES:
                addTvSeries(workOfCultureId, watchList);
                break;
            case GAME:
                addGame(workOfCultureId, watchList);
                break;
            default:
                throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);


        }
    }

    public void removeWorkOfCulture(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        WatchList watchList = getCurrentUserWatchList();

        switch (workOfCultureType) {
            case ANIME:
                removeAnime(workOfCultureId, watchList);
                break;
            case MANGA:
                removeManga(workOfCultureId, watchList);
                break;
            case MOVIE:
                removeMovie(workOfCultureId, watchList);
                break;
            case TVSERIES:
                removeTvSeries(workOfCultureId, watchList);
                break;
            case GAME:
                removeGame(workOfCultureId, watchList);
                break;
            default:
                throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);


        }
    }


    // ADD
    public void addAnime(UUID workOfCultureId, WatchList watchList) {
        watchList.getAnime().add(animeService.getById(workOfCultureId));
        save(watchList);
    }

    public void addManga(UUID workOfCultureId, WatchList watchList) {
        watchList.getManga().add(mangaService.getById(workOfCultureId));
        save(watchList);
    }

    public void addMovie(UUID workOfCultureId, WatchList watchList) {
        watchList.getMovies().add(movieService.getById(workOfCultureId));
        save(watchList);
    }

    public void addTvSeries(UUID workOfCultureId, WatchList watchList) {
        watchList.getTvSeries().add(tvSeriesService.getById(workOfCultureId));
        save(watchList);
    }

    public void addGame(UUID workOfCultureId, WatchList watchList) {
        watchList.getGames().add(gameService.getById(workOfCultureId));
        save(watchList);
    }

    //REMOVE

    public void removeAnime(UUID workOfCultureId, WatchList watchList) {
        watchList.getAnime().remove(animeService.getById(workOfCultureId));
        save(watchList);
    }

    public void removeManga(UUID workOfCultureId, WatchList watchList) {
        watchList.getManga().remove(mangaService.getById(workOfCultureId));
        save(watchList);
    }

    public void removeMovie(UUID workOfCultureId, WatchList watchList) {
        watchList.getMovies().remove(movieService.getById(workOfCultureId));
        save(watchList);
    }

    public void removeTvSeries(UUID workOfCultureId, WatchList watchList) {
        watchList.getTvSeries().remove(tvSeriesService.getById(workOfCultureId));
        save(watchList);
    }

    public void removeGame(UUID workOfCultureId, WatchList watchList) {
        watchList.getGames().remove(gameService.getById(workOfCultureId));
        save(watchList);
    }

}
