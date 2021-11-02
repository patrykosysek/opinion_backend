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

    public void addAnime(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Anime anime = animeService.getById(workOfCultureId);

        if (animeReviewService.existsByReviewListAndAnimeId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(ANIME_ALREADY_IN_REVIEW_LIST);
        }
        AnimeReview animeReview = new AnimeReview();
        animeReview.setComment(reviewCreateDTO.getReview());
        animeReview.addAnime(anime);
        animeReview.addReviewList(reviewList);
        animeReviewService.save(animeReview);
    }

    public void addManga(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Manga manga = mangaService.getById(workOfCultureId);

        if (mangaReviewService.existsByReviewListAndMangaId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(MANGA_ALREADY_IN_REVIEW_LIST);
        }
        MangaReview mangaReview = new MangaReview();
        mangaReview.setComment(reviewCreateDTO.getReview());
        mangaReview.addManga(manga);
        mangaReview.addReviewList(reviewList);
        mangaReviewService.save(mangaReview);
    }

    public void addMovie(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Movie movie = movieService.getById(workOfCultureId);

        if (movieReviewService.existsByReviewListAndMovieId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(MOVIE_ALREADY_IN_REVIEW_LIST);
        }
        MovieReview movieReview = new MovieReview();
        movieReview.setComment(reviewCreateDTO.getReview());
        movieReview.addMovie(movie);
        movieReview.addReviewList(reviewList);
        movieReviewService.save(movieReview);
    }

    public void addTvSeries(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);

        if (tvSeriesReviewService.existsByReviewListAndTvSeriesId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(TV_SERIES_ALREADY_IN_REVIEW_LIST);
        }
        TvSeriesReview tvSeriesReview = new TvSeriesReview();
        tvSeriesReview.setComment(reviewCreateDTO.getReview());
        tvSeriesReview.addTvSeries(tvSeries);
        tvSeriesReview.addReviewList(reviewList);
        tvSeriesReviewService.save(tvSeriesReview);
    }

    public void addGame(UUID workOfCultureId, ReviewList reviewList, ReviewCreateDTO reviewCreateDTO) {
        Game game = gameService.getById(workOfCultureId);

        if (gameReviewService.existsByReviewListAndGameId(reviewList, workOfCultureId)) {
            throw new IllegalArgumentException(GAME_ALREADY_IN_REVIEW_LIST);
        }
        GameReview gameReview = new GameReview();
        gameReview.setComment(reviewCreateDTO.getReview());
        gameReview.addGame(game);
        gameReview.addReviewList(reviewList);
        gameReviewService.save(gameReview);
    }


}
