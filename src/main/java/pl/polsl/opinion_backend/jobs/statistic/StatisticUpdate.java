package pl.polsl.opinion_backend.jobs.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeStatistic;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameStatistic;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaStatistic;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieStatistic;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesStatistic;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticUpdate {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;

    @Scheduled(cron = "@monthly")
    public void monthlyUpdate() {

        Iterable<Anime> animeList = animeService.findAll();
        animeList.forEach(anime -> {
            AnimeStatistic animeStatistic = anime.getStatistic();
            animeStatistic.setMonthDiscussion(animeStatistic.getCurrentDiscussion());
            animeStatistic.setMonthReview(animeStatistic.getCurrentReview());
            animeService.save(anime);
        });

        Iterable<Manga> mangaList = mangaService.findAll();
        mangaList.forEach(manga -> {
            MangaStatistic mangaStatistic = manga.getStatistic();
            mangaStatistic.setMonthDiscussion(mangaStatistic.getCurrentDiscussion());
            mangaStatistic.setMonthReview(mangaStatistic.getCurrentReview());
            mangaService.save(manga);
        });

        Iterable<Movie> movieList = movieService.findAll();
        movieList.forEach(movie -> {
            MovieStatistic movieStatistic = movie.getStatistic();
            movieStatistic.setMonthDiscussion(movieStatistic.getCurrentDiscussion());
            movieStatistic.setMonthReview(movieStatistic.getCurrentReview());
            movieService.save(movie);
        });

        Iterable<Game> gameList = gameService.findAll();
        gameList.forEach(game -> {
            GameStatistic gameStatistic = game.getStatistic();
            gameStatistic.setMonthDiscussion(gameStatistic.getCurrentDiscussion());
            gameStatistic.setMonthReview(gameStatistic.getCurrentReview());
            gameService.save(game);
        });

        Iterable<TvSeries> tvSeriesList = tvSeriesService.findAll();
        tvSeriesList.forEach(tvSeries -> {
            TvSeriesStatistic tvSeriesStatistic = tvSeries.getStatistic();
            tvSeriesStatistic.setMonthDiscussion(tvSeriesStatistic.getCurrentDiscussion());
            tvSeriesStatistic.setMonthReview(tvSeriesStatistic.getCurrentReview());
            tvSeriesService.save(tvSeries);
        });

    }

    @Scheduled(cron = "@weekly")
    public void weeklyUpdate() {

        Iterable<Anime> animeList = animeService.findAll();
        animeList.forEach(anime -> {
            AnimeStatistic animeStatistic = anime.getStatistic();
            animeStatistic.setWeekDiscussion(animeStatistic.getCurrentDiscussion());
            animeStatistic.setWeekReview(animeStatistic.getCurrentReview());
            animeService.save(anime);
        });

        Iterable<Manga> mangaList = mangaService.findAll();
        mangaList.forEach(manga -> {
            MangaStatistic mangaStatistic = manga.getStatistic();
            mangaStatistic.setWeekDiscussion(mangaStatistic.getCurrentDiscussion());
            mangaStatistic.setWeekReview(mangaStatistic.getCurrentReview());
            mangaService.save(manga);
        });

        Iterable<Movie> movieList = movieService.findAll();
        movieList.forEach(movie -> {
            MovieStatistic movieStatistic = movie.getStatistic();
            movieStatistic.setWeekDiscussion(movieStatistic.getCurrentDiscussion());
            movieStatistic.setWeekReview(movieStatistic.getCurrentReview());
            movieService.save(movie);
        });

        Iterable<Game> gameList = gameService.findAll();
        gameList.forEach(game -> {
            GameStatistic gameStatistic = game.getStatistic();
            gameStatistic.setWeekDiscussion(gameStatistic.getCurrentDiscussion());
            gameStatistic.setWeekReview(gameStatistic.getCurrentReview());
            gameService.save(game);
        });

        Iterable<TvSeries> tvSeriesList = tvSeriesService.findAll();
        tvSeriesList.forEach(tvSeries -> {
            TvSeriesStatistic tvSeriesStatistic = tvSeries.getStatistic();
            tvSeriesStatistic.setWeekDiscussion(tvSeriesStatistic.getCurrentDiscussion());
            tvSeriesStatistic.setWeekReview(tvSeriesStatistic.getCurrentReview());
            tvSeriesService.save(tvSeries);
        });

    }

}
