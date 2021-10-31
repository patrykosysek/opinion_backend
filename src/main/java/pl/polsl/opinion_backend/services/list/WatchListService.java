package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.list.WatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.list.watch.*;
import pl.polsl.opinion_backend.services.works.*;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@RequiredArgsConstructor
@Service
public class WatchListService extends BasicService<WatchList, WatchListRepository> {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;
    private final AnimeWatchListService animeWatchListService;
    private final MangaWatchListService mangaWatchListService;
    private final MovieWatchListService movieWatchListService;
    private final TvSeriesWatchListService tvSeriesWatchListService;
    private final GameWatchListService gameWatchListService;

    @Override
    public WatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }


    /////////////////////////////////////////////////////////////////////// ADD
    public void addAnime(UUID workOfCultureId, WatchList watchList) {
        Anime anime = animeService.getById(workOfCultureId);

        if (animeWatchListService.existsByWatchListAndAnime(watchList, anime)) {
            throw new IllegalArgumentException(ANIME_ALREADY_IN_WATCH_LIST);
        }
        AnimeWatchList animeWatchList = new AnimeWatchList();
        animeWatchList.addAnime(anime);
        animeWatchList.addWatchList(watchList);
        animeWatchListService.save(animeWatchList);
    }

    public void addManga(UUID workOfCultureId, WatchList watchList) {
        Manga manga = mangaService.getById(workOfCultureId);

        if (mangaWatchListService.existsByWatchListAndManga(watchList, manga)) {
            throw new IllegalArgumentException(MANGA_WATCH_LIST_NOT_FOUND);
        }
        MangaWatchList mangaWatchList = new MangaWatchList();
        mangaWatchList.addManga(manga);
        mangaWatchList.addWatchList(watchList);
        mangaWatchListService.save(mangaWatchList);
    }

    public void addMovie(UUID workOfCultureId, WatchList watchList) {
        Movie movie = movieService.getById(workOfCultureId);

        if (movieWatchListService.existsByWatchListAndMovie(watchList, movie)) {
            throw new IllegalArgumentException(MOVIE_ALREADY_IN_WATCH_LIST);
        }
        MovieWatchList movieWatchList = new MovieWatchList();
        movieWatchList.addMovie(movie);
        movieWatchList.addWatchList(watchList);
        movieWatchListService.save(movieWatchList);
    }

    public void addTvSeries(UUID workOfCultureId, WatchList watchList) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);

        if (tvSeriesWatchListService.existsByWatchListAndTvSeries(watchList, tvSeries)) {
            throw new IllegalArgumentException(TV_SERIES_ALREADY_IN_WATCH_LIST);
        }
        TvSeriesWatchList tvSeriesWatchList = new TvSeriesWatchList();
        tvSeriesWatchList.addTvSeries(tvSeries);
        tvSeriesWatchList.addWatchList(watchList);
        tvSeriesWatchListService.save(tvSeriesWatchList);
    }

    public void addGame(UUID workOfCultureId, WatchList watchList) {
        Game game = gameService.getById(workOfCultureId);

        if (gameWatchListService.existsByWatchListAndGame(watchList, game)) {
            throw new IllegalArgumentException(GAME_ALREADY_IN_WATCH_LIST);
        }

        GameWatchList gameWatchList = new GameWatchList();
        gameWatchList.addGame(game);
        gameWatchList.addWatchList(watchList);
        gameWatchListService.save(gameWatchList);
    }

    /////////////////////////////////////////////////////////////////////////// REMOVE

    public void removeAnime(UUID workOfCultureId, WatchList watchList) {
        animeWatchListService.delete(animeWatchListService.findByAnimeIdAndWatchList(workOfCultureId, watchList).getId());
    }

    public void removeManga(UUID workOfCultureId, WatchList watchList) {
        mangaWatchListService.delete(mangaWatchListService.findByMangaIdAndWatchList(workOfCultureId, watchList).getId());
    }

    public void removeMovie(UUID workOfCultureId, WatchList watchList) {
        movieWatchListService.delete(movieWatchListService.findByMovieIdAndWatchList(workOfCultureId, watchList).getId());
    }

    public void removeTvSeries(UUID workOfCultureId, WatchList watchList) {
        tvSeriesWatchListService.delete(tvSeriesWatchListService.findByTvSeriesIdAndWatchList(workOfCultureId, watchList).getId());
    }

    public void removeGame(UUID workOfCultureId, WatchList watchList) {
        gameWatchListService.delete(gameWatchListService.findByGameIdAndWatchList(workOfCultureId, watchList).getId());
    }

}
