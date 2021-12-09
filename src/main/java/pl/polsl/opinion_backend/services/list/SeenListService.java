package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.list.SeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.list.seen.*;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@RequiredArgsConstructor
@Service
public class SeenListService extends BasicService<SeenList, SeenListRepository> {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;
    private final AnimeSeenListService animeSeenListService;
    private final MangaSeenListService mangaSeenListService;
    private final MovieSeenListService movieSeenListService;
    private final TvSeriesSeenListService tvSeriesSeenListService;
    private final GameSeenListService gameSeenListService;

    @Override
    public SeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }


    //////////////////////////////////////////////////////////////////////// ADD

    public void addAnime(UUID workOfCultureId, SeenList seenList) {
        Anime anime = animeService.getById(workOfCultureId);

        if (animeSeenListService.existsBySeenListAndAnime(seenList, anime)) {
            throw new IllegalArgumentException(ANIME_ALREADY_IN_SEEN_LIST);
        }
        AnimeSeenList animeSeenList = new AnimeSeenList();
        animeSeenList.addAnime(anime);
        animeSeenList.addSeenList(seenList);
        animeSeenListService.save(animeSeenList);
    }

    public void addManga(UUID workOfCultureId, SeenList seenList) {
        Manga manga = mangaService.getById(workOfCultureId);

        if (mangaSeenListService.existsBySeenListAndManga(seenList, manga)) {
            throw new IllegalArgumentException(MANGA_ALREADY_IN_SEEN_LIST);
        }
        MangaSeenList mangaSeenList = new MangaSeenList();
        mangaSeenList.addManga(manga);
        mangaSeenList.addSeenList(seenList);
        mangaSeenListService.save(mangaSeenList);
    }

    public void addMovie(UUID workOfCultureId, SeenList seenList) {
        Movie movie = movieService.getById(workOfCultureId);

        if (movieSeenListService.existsBySeenListAndMovie(seenList, movie)) {
            throw new IllegalArgumentException(MOVIE_ALREADY_IN_SEEN_LIST);
        }
        MovieSeenList movieSeenList = new MovieSeenList();
        movieSeenList.addMovie(movie);
        movieSeenList.addSeenList(seenList);
        movieSeenListService.save(movieSeenList);
    }

    public void addTvSeries(UUID workOfCultureId, SeenList seenList) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);

        if (tvSeriesSeenListService.existsBySeenListAndTvSeries(seenList, tvSeries)) {
            throw new IllegalArgumentException(TV_SERIES_ALREADY_IN_SEEN_LIST);
        }
        TvSeriesSeenList tvSeriesSeenList = new TvSeriesSeenList();
        tvSeriesSeenList.addTvSeries(tvSeries);
        tvSeriesSeenList.addSeenList(seenList);
        tvSeriesSeenListService.save(tvSeriesSeenList);
    }

    public void addGame(UUID workOfCultureId, SeenList seenList) {
        Game game = gameService.getById(workOfCultureId);

        if (gameSeenListService.existsBySeenListAndGame(seenList, game)) {
            throw new IllegalArgumentException(GAME_ALREADY_IN_SEEN_LIST);
        }
        GameSeenList gameSeenList = new GameSeenList();
        gameSeenList.addGame(game);
        gameSeenList.addSeenList(seenList);
        gameSeenListService.save(gameSeenList);
    }

}
