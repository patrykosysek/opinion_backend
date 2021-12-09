package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.list.ReviewCreateDTO;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.repositories.list.ReviewListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.list.review.*;
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
public class ReviewListService extends BasicService<ReviewList, ReviewListRepository> {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;
    private final AnimeReviewService animeReviewService;
    private final MangaReviewService mangaReviewService;
    private final MovieReviewService movieReviewService;
    private final GameReviewService gameReviewService;
    private final TvSeriesReviewService tvSeriesReviewService;


    @Override
    public ReviewList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    //////////////////////////////////////////////////////////////////////// ADD

    public AnimeReview addAnime(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Anime anime = animeService.getById(workOfCultureId);

        if (animeReviewService.existsByReviewListAndAnimeId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(ANIME_ALREADY_IN_REVIEW_LIST);
        }
        AnimeReview animeReview = new AnimeReview();
        animeReview.setComment(reviewCreateDTO.getReview());
        animeReview.addAnime(anime);
        animeReview.addReviewList(reviewList);
        anime.getStatistic().setCurrentReview(anime.getStatistic().getCurrentReview() + 1);
        return animeReviewService.save(animeReview);
    }

    public MangaReview addManga(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Manga manga = mangaService.getById(workOfCultureId);

        if (mangaReviewService.existsByReviewListAndMangaId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(MANGA_ALREADY_IN_REVIEW_LIST);
        }
        MangaReview mangaReview = new MangaReview();
        mangaReview.setComment(reviewCreateDTO.getReview());
        mangaReview.addManga(manga);
        mangaReview.addReviewList(reviewList);
        manga.getStatistic().setCurrentReview(manga.getStatistic().getCurrentReview() + 1);
        return mangaReviewService.save(mangaReview);
    }

    public MovieReview addMovie(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Movie movie = movieService.getById(workOfCultureId);

        if (movieReviewService.existsByReviewListAndMovieId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(MOVIE_ALREADY_IN_REVIEW_LIST);
        }
        MovieReview movieReview = new MovieReview();
        movieReview.setComment(reviewCreateDTO.getReview());
        movieReview.addMovie(movie);
        movieReview.addReviewList(reviewList);
        movie.getStatistic().setCurrentReview(movie.getStatistic().getCurrentReview() + 1);
        return movieReviewService.save(movieReview);
    }

    public TvSeriesReview addTvSeries(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);

        if (tvSeriesReviewService.existsByReviewListAndTvSeriesId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(TV_SERIES_ALREADY_IN_REVIEW_LIST);
        }
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        tvSeriesReview.setComment(reviewCreateDTO.getReview());
        tvSeriesReview.addTvSeries(tvSeries);
        tvSeriesReview.addReviewList(reviewList);
        tvSeries.getStatistic().setCurrentReview(tvSeries.getStatistic().getCurrentReview() + 1);
        return tvSeriesReviewService.save(tvSeriesReview);
    }

    public GameReview addGame(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Game game = gameService.getById(workOfCultureId);

        if (gameReviewService.existsByReviewListAndGameId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(GAME_ALREADY_IN_REVIEW_LIST);
        }
        GameReview gameReview = new GameReview();
        gameReview.setComment(reviewCreateDTO.getReview());
        gameReview.addGame(game);
        gameReview.addReviewList(reviewList);
        game.getStatistic().setCurrentReview(game.getStatistic().getCurrentReview() + 1);
       // gameService.save(game);
        return gameReviewService.save(gameReview);
    }


}
