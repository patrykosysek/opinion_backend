package pl.polsl.opinion_backend.services.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.dtos.list.ReviewCreateDTO;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.list.review.*;
import pl.polsl.opinion_backend.services.list.watch.*;
import pl.polsl.opinion_backend.services.user.UserService;

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
    public void addWorkOfCultureWithReview(WorkOfCultureType workOfCultureType, UUID workOfCultureId, ReviewCreateDTO reviewCreateDTO) {
        UUID userId = userService.getCurrentUser().getId();
        User user = userService.getById(userId);
        ReviewList reviewList = user.getReviewList();

        switch (workOfCultureType) {
            case ANIME -> {
                reviewListService.addAnime(workOfCultureId, reviewList, reviewCreateDTO);
            }
            case MANGA -> {
                reviewListService.addManga(workOfCultureId, reviewList, reviewCreateDTO);

            }
            case MOVIE -> {
                reviewListService.addMovie(workOfCultureId, reviewList, reviewCreateDTO);

            }
            case TVSERIES -> {
                reviewListService.addTvSeries(workOfCultureId, reviewList, reviewCreateDTO);
            }
            case GAME -> {
                reviewListService.addGame(workOfCultureId, reviewList, reviewCreateDTO);
            }
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }

        addWorkOfCultureToSeenListWithoutReview(workOfCultureType, workOfCultureId);

    }
}
