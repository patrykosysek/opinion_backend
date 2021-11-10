package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.dtos.list.ReviewCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.*;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.review.*;
import pl.polsl.opinion_backend.services.list.review.*;
import pl.polsl.opinion_backend.services.list.seen.*;
import pl.polsl.opinion_backend.services.list.watch.*;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ListManagingService {
    private final WatchListService watchListService;
    private final SeenListService seenListService;
    private final UserService userService;

    private final AnimeWatchListService animeWatchListService;
    private final MangaWatchListService mangaWatchListService;
    private final MovieWatchListService movieWatchListService;
    private final TvSeriesWatchListService tvSeriesWatchListService;
    private final GameWatchListService gameWatchListService;

    private final AnimeReviewService animeReviewService;
    private final MangaReviewService mangaReviewService;
    private final MovieReviewService movieReviewService;
    private final GameReviewService gameReviewService;
    private final TvSeriesReviewService tvSeriesReviewService;
    private final ReviewListService reviewListService;

    private final AnimeSeenListService animeSeenListService;
    private final MangaSeenListService mangaSeenListService;
    private final MovieSeenListService movieSeenListService;
    private final GameSeenListService gameSeenListService;
    private final TvSeriesSeenListService tvSeriesSeenListService;

    private final AnimeMapper animeMapper;
    private final MangaMapper mangaMapper;
    private final MovieMapper movieMapper;
    private final GameMapper gameMapper;
    private final TvSeriesMapper tvSeriesMapper;

    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;

    private final AnimeReviewMapper animeReviewMapper;
    private final MangaReviewMapper mangaReviewMapper;
    private final MovieReviewMapper movieReviewMapper;
    private final GameReviewMapper gameReviewMapper;
    private final TvSeriesReviewMapper tvSeriesReviewMapper;

    public WatchList getCurrentUserWatchList() {
        return userService.getCurrentUser().getWatchList();
    }

    public SeenList getCurrentUserSeenList() {
        return userService.getCurrentUser().getSeenList();
    }

    @Transactional
    public void addWorkOfCultureToWatchList(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        WatchList watchList = user.getWatchList();

        switch (workOfCultureType) {
            case ANIME -> {
                watchListService.addAnime(workOfCultureId, watchList);
            }
            case MANGA -> {
                watchListService.addManga(workOfCultureId, watchList);
            }
            case MOVIE -> {
                watchListService.addMovie(workOfCultureId, watchList);
            }
            case TVSERIES -> {
                watchListService.addTvSeries(workOfCultureId, watchList);
            }
            case GAME -> {
                watchListService.addGame(workOfCultureId, watchList);
            }
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }
    }

    public void addWorkOfCultureToSeenListWithoutReview(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        WatchList watchList = user.getWatchList();
        SeenList seenList = user.getSeenList();

        switch (workOfCultureType) {
            case ANIME -> {
                Anime anime = animeService.getById(workOfCultureId);
                if (!animeSeenListService.existsBySeenListAndAnime(seenList, anime)) {
                    seenListService.addAnime(workOfCultureId, seenList);
                    if (animeWatchListService.existsByWatchListAndAnimeId(watchList, workOfCultureId))
                        watchListService.removeAnime(workOfCultureId, watchList);
                }
            }
            case MANGA -> {
                Manga manga = mangaService.getById(workOfCultureId);
                if (!mangaSeenListService.existsBySeenListAndManga(seenList, manga)) {
                    seenListService.addManga(workOfCultureId, seenList);
                    if (mangaWatchListService.existsByWatchListAndMangaId(watchList, workOfCultureId))
                        watchListService.removeManga(workOfCultureId, watchList);
                }
            }
            case MOVIE -> {
                Movie movie = movieService.getById(workOfCultureId);
                if (!movieSeenListService.existsBySeenListAndMovie(seenList, movie)) {
                    seenListService.addMovie(workOfCultureId, seenList);
                    if (movieWatchListService.existsByWatchListAndMovieId(watchList, workOfCultureId))
                        watchListService.removeMovie(workOfCultureId, watchList);
                }
            }
            case TVSERIES -> {
                TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);
                if (!tvSeriesSeenListService.existsBySeenListAndTvSeries(seenList, tvSeries)) {
                    seenListService.addTvSeries(workOfCultureId, seenList);
                    if (tvSeriesWatchListService.existsByWatchListAndTvSeriesId(watchList, workOfCultureId))
                        watchListService.removeTvSeries(workOfCultureId, watchList);
                }
            }
            case GAME -> {
                Game game = gameService.getById(workOfCultureId);
                if (!gameSeenListService.existsBySeenListAndGame(seenList, game)) {
                    seenListService.addGame(workOfCultureId, seenList);
                    if (gameWatchListService.existsByWatchListAndGameId(watchList, workOfCultureId))
                        watchListService.removeGame(workOfCultureId, watchList);
                }
            }

        }
    }

    public void addWorkOfCultureToSeenList(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        WatchList watchList = user.getWatchList();
        SeenList seenList = user.getSeenList();

        switch (workOfCultureType) {
            case ANIME -> {
                seenListService.addAnime(workOfCultureId, seenList);
                if (animeWatchListService.existsByWatchListAndAnimeId(watchList, workOfCultureId))
                    watchListService.removeAnime(workOfCultureId, watchList);
            }
            case MANGA -> {
                seenListService.addManga(workOfCultureId, seenList);
                if (mangaWatchListService.existsByWatchListAndMangaId(watchList, workOfCultureId))
                    watchListService.removeManga(workOfCultureId, watchList);

            }
            case MOVIE -> {
                seenListService.addMovie(workOfCultureId, seenList);
                if (movieWatchListService.existsByWatchListAndMovieId(watchList, workOfCultureId))
                    watchListService.removeMovie(workOfCultureId, watchList);
            }
            case TVSERIES -> {
                seenListService.addTvSeries(workOfCultureId, seenList);
                if (tvSeriesWatchListService.existsByWatchListAndTvSeriesId(watchList, workOfCultureId))
                    watchListService.removeTvSeries(workOfCultureId, watchList);
            }
            case GAME -> {
                seenListService.addGame(workOfCultureId, seenList);
                if (gameWatchListService.existsByWatchListAndGameId(watchList, workOfCultureId))
                    watchListService.removeGame(workOfCultureId, watchList);
            }
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }
    }


    public void removeWorkOfCulture(WorkOfCultureType workOfCultureType, UUID workOfCultureId) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        WatchList watchList = user.getWatchList();

        switch (workOfCultureType) {
            case ANIME -> watchListService.removeAnime(workOfCultureId, watchList);
            case MANGA -> watchListService.removeManga(workOfCultureId, watchList);
            case MOVIE -> watchListService.removeMovie(workOfCultureId, watchList);
            case TVSERIES -> watchListService.removeTvSeries(workOfCultureId, watchList);
            case GAME -> watchListService.removeGame(workOfCultureId, watchList);
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }
    }


    @Transactional
    public ReviewResponseDTO addWorkOfCultureWithReview(WorkOfCultureType workOfCultureType, UUID
            workOfCultureId, ReviewCreateDTO reviewCreateDTO) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        ReviewList reviewList = user.getReviewList();

        addWorkOfCultureToSeenListWithoutReview(workOfCultureType, workOfCultureId);

        return switch (workOfCultureType) {
            case ANIME -> animeReviewMapper.toReviewResponseDTO(reviewListService.addAnime(workOfCultureId, reviewList, reviewCreateDTO));

            case MANGA -> mangaReviewMapper.toReviewResponseDTO(reviewListService.addManga(workOfCultureId, reviewList, reviewCreateDTO));

            case MOVIE -> movieReviewMapper.toReviewResponseDTO(reviewListService.addMovie(workOfCultureId, reviewList, reviewCreateDTO));

            case TVSERIES -> tvSeriesReviewMapper.toReviewResponseDTO(reviewListService.addTvSeries(workOfCultureId, reviewList, reviewCreateDTO));

            case GAME -> gameReviewMapper.toReviewResponseDTO(reviewListService.addGame(workOfCultureId, reviewList, reviewCreateDTO));
        };

        //  addWorkOfCultureToSeenListWithoutReview(workOfCultureType, workOfCultureId);

    }

    public Page<WorkOfCultureResponseDTO> getWatchListWorks(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        WatchList watchList = user.getWatchList();

        return switch (workOfCultureType) {
            case ANIME -> animeWatchListService.getAllByWatchList(watchList, pageable).map(AnimeWatchList::getAnime).map(animeMapper::toWorkOfCultureResponseDTO);
            case MANGA -> mangaWatchListService.getAllByWatchList(watchList, pageable).map(MangaWatchList::getManga).map(mangaMapper::toWorkOfCultureResponseDTO);
            case MOVIE -> movieWatchListService.getAllByWatchList(watchList, pageable).map(MovieWatchList::getMovie).map(movieMapper::toWorkOfCultureResponseDTO);
            case TVSERIES -> tvSeriesWatchListService.getAllByWatchList(watchList, pageable).map(TvSeriesWatchList::getTvSeries).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
            case GAME -> gameWatchListService.getAllByWatchList(watchList, pageable).map(GameWatchList::getGame).map(gameMapper::toWorkOfCultureResponseDTO);
        };

    }

    public Page<WorkOfCultureResponseDTO> getSeenListWorks(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        SeenList seenList = user.getSeenList();

        return switch (workOfCultureType) {
            case ANIME -> animeSeenListService.getAllBySeenList(seenList, pageable).map(AnimeSeenList::getAnime).map(animeMapper::toWorkOfCultureResponseDTO);
            case MANGA -> mangaSeenListService.getAllBySeenList(seenList, pageable).map(MangaSeenList::getManga).map(mangaMapper::toWorkOfCultureResponseDTO);
            case MOVIE -> movieSeenListService.getAllBySeenList(seenList, pageable).map(MovieSeenList::getMovie).map(movieMapper::toWorkOfCultureResponseDTO);
            case TVSERIES -> tvSeriesSeenListService.getAllBySeenList(seenList, pageable).map(TvSeriesSeenList::getTvSeries).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
            case GAME -> gameSeenListService.getAllBySeenList(seenList, pageable).map(GameSeenList::getGame).map(gameMapper::toWorkOfCultureResponseDTO);
        };

    }

    public Page<ReviewResponseDTO> getReviewListWorks(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        ReviewList reviewList = user.getReviewList();

        return switch (workOfCultureType) {
            case ANIME -> animeReviewService.getAllByReviewList(reviewList, pageable).map(animeReviewMapper::toReviewResponseDTO);
            case MANGA -> mangaReviewService.getAllByReviewList(reviewList, pageable).map(mangaReviewMapper::toReviewResponseDTO);
            case MOVIE -> movieReviewService.getAllByReviewList(reviewList, pageable).map(movieReviewMapper::toReviewResponseDTO);
            case TVSERIES -> tvSeriesReviewService.getAllByReviewList(reviewList, pageable).map(tvSeriesReviewMapper::toReviewResponseDTO);
            case GAME -> gameReviewService.getAllByReviewList(reviewList, pageable).map(gameReviewMapper::toReviewResponseDTO);
        };

    }


}
