package pl.polsl.opinion_backend.services.works.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReviewLike;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReviewLike;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReviewLike;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReviewLike;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReviewLike;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.review.*;
import pl.polsl.opinion_backend.services.list.review.*;
import pl.polsl.opinion_backend.services.list.review.like.*;
import pl.polsl.opinion_backend.services.user.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_ALREADY_DISLIKED;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_ALREADY_LIKED;

@RequiredArgsConstructor
@Service
public class ReviewManagingService {
    private final AnimeReviewService animeReviewService;
    private final MangaReviewService mangaReviewService;
    private final MovieReviewService movieReviewService;
    private final TvSeriesReviewService tvSeriesReviewService;
    private final GameReviewService gameReviewService;

    private final AnimeReviewMapper animeReviewMapper;
    private final MangaReviewMapper mangaReviewMapper;
    private final MovieReviewMapper movieReviewMapper;
    private final TvSeriesReviewMapper tvSeriesReviewMapper;
    private final GameReviewMapper gameReviewMapper;

    private final AnimeReviewLikeService animeReviewLikeService;
    private final MangaReviewLikeService mangaReviewLikeService;
    private final MovieReviewLikeService movieReviewLikeService;
    private final GameReviewLikeService gameReviewLikeService;
    private final TvSeriesReviewLikeService tvSeriesReviewLikeService;

    private final UserService userService;

    public ReviewResponseDTO getReviewInformation(WorkOfCultureType workOfCultureType, UUID id) {

        return switch (workOfCultureType) {
            case ANIME -> animeReviewMapper.toReviewResponseDTO(animeReviewService.getById(id));
            case MANGA -> mangaReviewMapper.toReviewResponseDTO(mangaReviewService.getById(id));
            case MOVIE -> movieReviewMapper.toReviewResponseDTO(movieReviewService.getById(id));
            case TVSERIES -> tvSeriesReviewMapper.toReviewResponseDTO(tvSeriesReviewService.getById(id));
            case GAME -> gameReviewMapper.toReviewResponseDTO(gameReviewService.getById(id));
        };

    }

    public ReviewResponseDTO likeReview(UUID id, WorkOfCultureType workOfCultureType) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {

            case ANIME -> animeReviewMapper.toReviewResponseDTO(likeAnimeReview(id, userId));
            case MANGA -> mangaReviewMapper.toReviewResponseDTO(likeMangaReview(id, userId));
            case MOVIE -> movieReviewMapper.toReviewResponseDTO(likeMovieReview(id, userId));
            case TVSERIES -> tvSeriesReviewMapper.toReviewResponseDTO(likeTvSeriesReview(id, userId));
            case GAME -> gameReviewMapper.toReviewResponseDTO(likeGameReview(id, userId));
        };

    }

    public ReviewResponseDTO dislikeReview(UUID id, WorkOfCultureType workOfCultureType) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {

            case ANIME -> animeReviewMapper.toReviewResponseDTO(dislikeAnimeReview(id, userId));
            case MANGA -> mangaReviewMapper.toReviewResponseDTO(dislikeMangaReview(id, userId));
            case MOVIE -> movieReviewMapper.toReviewResponseDTO(dislikeMovieReview(id, userId));
            case TVSERIES -> tvSeriesReviewMapper.toReviewResponseDTO(dislikeTvSeriesReview(id, userId));
            case GAME -> gameReviewMapper.toReviewResponseDTO(dislikeGameReview(id, userId));
        };

    }


    public AnimeReview likeAnimeReview(UUID id, UUID userId) {
        AnimeReview animeReview = animeReviewService.getById(id);

        if (animeReviewLikeService.existsByAnimeReviewAndCreateBy(animeReview, userId)) {
            AnimeReviewLike animeReviewLike = animeReviewLikeService.findByAnimeReviewAndCreateBy(animeReview, userId);
            if (!animeReviewLike.isPositive()) {
                animeReviewLike.setPositive(true);
                animeReviewLikeService.save(animeReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_LIKED);
        } else {
            AnimeReviewLike animeReviewLike = new AnimeReviewLike();
            animeReviewLike.setPositive(true);
            animeReview.addLike(animeReviewLike);
            animeReviewLikeService.save(animeReviewLike);
        }
        return animeReview;
    }

    public MangaReview likeMangaReview(UUID id, UUID userId) {
        MangaReview mangaReview = mangaReviewService.getById(id);

        if (mangaReviewLikeService.existsByMangaReviewAndCreateBy(mangaReview, userId)) {
            MangaReviewLike mangaReviewLike = mangaReviewLikeService.findByMangaReviewAndCreateBy(mangaReview, userId);
            if (!mangaReviewLike.isPositive()) {
                mangaReviewLike.setPositive(true);
                mangaReviewLikeService.save(mangaReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_LIKED);
        } else {
            MangaReviewLike mangaReviewLike = new MangaReviewLike();
            mangaReviewLike.setPositive(true);
            mangaReview.addLike(mangaReviewLike);
            mangaReviewLikeService.save(mangaReviewLike);
        }
        return mangaReview;
    }

    public MovieReview likeMovieReview(UUID id, UUID userId) {
        MovieReview movieReview = movieReviewService.getById(id);

        if (movieReviewLikeService.existsByMovieReviewAndCreateBy(movieReview, userId)) {
            MovieReviewLike movieReviewLike = movieReviewLikeService.findByMovieReviewAndCreateBy(movieReview, userId);
            if (!movieReviewLike.isPositive()) {
                movieReviewLike.setPositive(true);
                movieReviewLikeService.save(movieReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_LIKED);
        } else {
            MovieReviewLike movieReviewLike = new MovieReviewLike();
            movieReviewLike.setPositive(true);
            movieReview.addLike(movieReviewLike);
            movieReviewLikeService.save(movieReviewLike);
        }
        return movieReview;
    }

    public TvSeriesReview likeTvSeriesReview(UUID id, UUID userId) {
        TvSeriesReview tvSeriesReview = tvSeriesReviewService.getById(id);

        if (tvSeriesReviewLikeService.existsByTvSeriesReviewAndCreateBy(tvSeriesReview, userId)) {
            TvSeriesReviewLike tvSeriesReviewLike = tvSeriesReviewLikeService.findByTvSeriesReviewAndCreateBy(tvSeriesReview, userId);
            if (!tvSeriesReviewLike.isPositive()) {
                tvSeriesReviewLike.setPositive(true);
                tvSeriesReviewLikeService.save(tvSeriesReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_LIKED);
        } else {
            TvSeriesReviewLike tvSeriesReviewLike = new TvSeriesReviewLike();
            tvSeriesReviewLike.setPositive(true);
            tvSeriesReview.addLike(tvSeriesReviewLike);
            tvSeriesReviewLikeService.save(tvSeriesReviewLike);
        }
        return tvSeriesReview;
    }

    public GameReview likeGameReview(UUID id, UUID userId) {
        GameReview gameReview = gameReviewService.getById(id);

        if (gameReviewLikeService.existsByGameReviewAndCreateBy(gameReview, userId)) {
            GameReviewLike gameReviewLike = gameReviewLikeService.findByGameReviewAndCreateBy(gameReview, userId);
            if (!gameReviewLike.isPositive()) {
                gameReviewLike.setPositive(true);
                gameReviewLikeService.save(gameReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_LIKED);
        } else {
            GameReviewLike gameReviewLike = new GameReviewLike();
            gameReviewLike.setPositive(true);
            gameReview.addLike(gameReviewLike);
            gameReviewLikeService.save(gameReviewLike);
        }
        return gameReview;
    }


    public AnimeReview dislikeAnimeReview(UUID id, UUID userId) {
        AnimeReview animeReview = animeReviewService.getById(id);

        if (animeReviewLikeService.existsByAnimeReviewAndCreateBy(animeReview, userId)) {
            AnimeReviewLike animeReviewLike = animeReviewLikeService.findByAnimeReviewAndCreateBy(animeReview, userId);
            if (animeReviewLike.isPositive()) {
                animeReviewLike.setPositive(false);
                animeReviewLikeService.save(animeReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_DISLIKED);
        } else {
            AnimeReviewLike animeReviewLike = new AnimeReviewLike();
            animeReviewLike.setPositive(false);
            animeReview.addLike(animeReviewLike);
            animeReviewLikeService.save(animeReviewLike);
        }
        return animeReview;
    }

    public MangaReview dislikeMangaReview(UUID id, UUID userId) {
        MangaReview mangaReview = mangaReviewService.getById(id);

        if (mangaReviewLikeService.existsByMangaReviewAndCreateBy(mangaReview, userId)) {
            MangaReviewLike mangaReviewLike = mangaReviewLikeService.findByMangaReviewAndCreateBy(mangaReview, userId);
            if (mangaReviewLike.isPositive()) {
                mangaReviewLike.setPositive(false);
                mangaReviewLikeService.save(mangaReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_DISLIKED);
        } else {
            MangaReviewLike mangaReviewLike = new MangaReviewLike();
            mangaReviewLike.setPositive(false);
            mangaReview.addLike(mangaReviewLike);
            mangaReviewLikeService.save(mangaReviewLike);
        }
        return mangaReview;
    }

    public MovieReview dislikeMovieReview(UUID id, UUID userId) {
        MovieReview movieReview = movieReviewService.getById(id);

        if (movieReviewLikeService.existsByMovieReviewAndCreateBy(movieReview, userId)) {
            MovieReviewLike movieReviewLike = movieReviewLikeService.findByMovieReviewAndCreateBy(movieReview, userId);
            if (movieReviewLike.isPositive()) {
                movieReviewLike.setPositive(false);
                movieReviewLikeService.save(movieReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_DISLIKED);
        } else {
            MovieReviewLike movieReviewLike = new MovieReviewLike();
            movieReviewLike.setPositive(false);
            movieReview.addLike(movieReviewLike);
            movieReviewLikeService.save(movieReviewLike);
        }
        return movieReview;
    }

    public TvSeriesReview dislikeTvSeriesReview(UUID id, UUID userId) {
        TvSeriesReview tvSeriesReview = tvSeriesReviewService.getById(id);

        if (tvSeriesReviewLikeService.existsByTvSeriesReviewAndCreateBy(tvSeriesReview, userId)) {
            TvSeriesReviewLike tvSeriesReviewLike = tvSeriesReviewLikeService.findByTvSeriesReviewAndCreateBy(tvSeriesReview, userId);
            if (tvSeriesReviewLike.isPositive()) {
                tvSeriesReviewLike.setPositive(false);
                tvSeriesReviewLikeService.save(tvSeriesReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_DISLIKED);
        } else {
            TvSeriesReviewLike tvSeriesReviewLike = new TvSeriesReviewLike();
            tvSeriesReviewLike.setPositive(false);
            tvSeriesReview.addLike(tvSeriesReviewLike);
            tvSeriesReviewLikeService.save(tvSeriesReviewLike);
        }
        return tvSeriesReview;
    }

    public GameReview dislikeGameReview(UUID id, UUID userId) {
        GameReview gameReview = gameReviewService.getById(id);

        if (gameReviewLikeService.existsByGameReviewAndCreateBy(gameReview, userId)) {
            GameReviewLike gameReviewLike = gameReviewLikeService.findByGameReviewAndCreateBy(gameReview, userId);
            if (gameReviewLike.isPositive()) {
                gameReviewLike.setPositive(false);
                gameReviewLikeService.save(gameReviewLike);
            } else throw new IllegalArgumentException(REVIEW_ALREADY_DISLIKED);
        } else {
            GameReviewLike gameReviewLike = new GameReviewLike();
            gameReviewLike.setPositive(false);
            gameReview.addLike(gameReviewLike);
            gameReviewLikeService.save(gameReviewLike);
        }
        return gameReview;
    }

    public Page<ReviewResponseDTO> getReviewsByDate(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable, boolean oldest) {
        return oldest ? getWorkOfCultureByDateAsc(workOfCultureType, workOfCultureId, pageable) : getWorkOfCultureByDateDesc(workOfCultureType, workOfCultureId, pageable);
    }

    public Page<ReviewResponseDTO> getReviewsByLikes(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable, boolean favourite) {
        return favourite ? getWorkOfCultureReviewsByLikesDesc(workOfCultureType, workOfCultureId, pageable) : getWorkOfCultureReviewsByLikesAsc(workOfCultureType, workOfCultureId, pageable);
    }


    public Page<ReviewResponseDTO> getWorkOfCultureReviews(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeReviewService.findAllByAnimeId(workOfCultureId, pageable).map(animeReviewMapper::toReviewResponseDTO);
            case MANGA -> mangaReviewService.findAllByMangaId(workOfCultureId, pageable).map(mangaReviewMapper::toReviewResponseDTO);
            case MOVIE -> movieReviewService.findAllByMovieId(workOfCultureId, pageable).map(movieReviewMapper::toReviewResponseDTO);
            case TVSERIES -> tvSeriesReviewService.findAllByTvSeriesId(workOfCultureId, pageable).map(tvSeriesReviewMapper::toReviewResponseDTO);
            case GAME -> gameReviewService.findAllByGameId(workOfCultureId, pageable).map(gameReviewMapper::toReviewResponseDTO);
        };
    }

    public Page<ReviewResponseDTO> getWorkOfCultureByDateAsc(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeReviewService.findAllByAnimeIdOrderByCreateDateAsc(workOfCultureId, pageable).map(animeReviewMapper::toReviewResponseDTO);
            case MANGA -> mangaReviewService.findAllByMangaIdOrderByCreateDateAsc(workOfCultureId, pageable).map(mangaReviewMapper::toReviewResponseDTO);
            case MOVIE -> movieReviewService.findAllByMovieIdOrderByCreateDateAsc(workOfCultureId, pageable).map(movieReviewMapper::toReviewResponseDTO);
            case TVSERIES -> tvSeriesReviewService.findAllByTvSeriesIdOrderByCreateDateAsc(workOfCultureId, pageable).map(tvSeriesReviewMapper::toReviewResponseDTO);
            case GAME -> gameReviewService.findAllByGameIdOrderByCreateDateAsc(workOfCultureId, pageable).map(gameReviewMapper::toReviewResponseDTO);
        };
    }

    public Page<ReviewResponseDTO> getWorkOfCultureByDateDesc(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeReviewService.findAllByAnimeIdOrderByCreateDateDesc(workOfCultureId, pageable).map(animeReviewMapper::toReviewResponseDTO);
            case MANGA -> mangaReviewService.findAllByMangaIdOrderByCreateDateDesc(workOfCultureId, pageable).map(mangaReviewMapper::toReviewResponseDTO);
            case MOVIE -> movieReviewService.findAllByMovieIdOrderByCreateDateDesc(workOfCultureId, pageable).map(movieReviewMapper::toReviewResponseDTO);
            case TVSERIES -> tvSeriesReviewService.findAllByTvSeriesIdOrderByCreateDateDesc(workOfCultureId, pageable).map(tvSeriesReviewMapper::toReviewResponseDTO);
            case GAME -> gameReviewService.findAllByGameIdOrderByCreateDateDesc(workOfCultureId, pageable).map(gameReviewMapper::toReviewResponseDTO);
        };
    }

    public Page<ReviewResponseDTO> getWorkOfCultureReviewsByLikesAsc(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> getAnimeReviews(workOfCultureId, pageable);
            case MANGA -> getMangaReviews(workOfCultureId, pageable);
            case MOVIE -> getMovieReviews(workOfCultureId, pageable);
            case TVSERIES -> getTvSeriesReviews(workOfCultureId, pageable);
            case GAME -> getGameReviews(workOfCultureId, pageable);
        };
    }

    public Page<ReviewResponseDTO> getWorkOfCultureReviewsByLikesDesc(WorkOfCultureType workOfCultureType, UUID workOfCultureId, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> getAnimeReviewsDesc(workOfCultureId, pageable);
            case MANGA -> getMangaReviewsDesc(workOfCultureId, pageable);
            case MOVIE -> getMovieReviewsDesc(workOfCultureId, pageable);
            case TVSERIES -> getTvSeriesReviewsDesc(workOfCultureId, pageable);
            case GAME -> getGameReviewsDesc(workOfCultureId, pageable);
        };
    }


    public Page<ReviewResponseDTO> getAnimeReviews(UUID id, Pageable pageable) {
        Page<AnimeReview> animeReview = animeReviewService.findAllByAnimeId(id, pageable);
        List<AnimeReview> animeReviews = animeReview.stream().sorted(Comparator.comparingInt(animeReviewLikeService::getLike)).collect(Collectors.toList());
        return new PageImpl<>(animeReviews, pageable, animeReviews.size()).map(animeReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getMangaReviews(UUID id, Pageable pageable) {
        Page<MangaReview> mangaReview = mangaReviewService.findAllByMangaId(id, pageable);
        List<MangaReview> mangaReviews = mangaReview.stream().sorted(Comparator.comparingInt(mangaReviewLikeService::getLike)).collect(Collectors.toList());
        return new PageImpl<>(mangaReviews, pageable, mangaReviews.size()).map(mangaReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getMovieReviews(UUID id, Pageable pageable) {
        Page<MovieReview> movieReview = movieReviewService.findAllByMovieId(id, pageable);
        List<MovieReview> movieReviews = movieReview.stream().sorted(Comparator.comparingInt(movieReviewLikeService::getLike)).collect(Collectors.toList());
        return new PageImpl<>(movieReviews, pageable, movieReviews.size()).map(movieReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getGameReviews(UUID id, Pageable pageable) {
        Page<GameReview> gameReview = gameReviewService.findAllByGameId(id, pageable);
        List<GameReview> gameReviews = gameReview.stream().sorted(Comparator.comparingInt(gameReviewLikeService::getLike)).collect(Collectors.toList());
        return new PageImpl<>(gameReviews, pageable, gameReviews.size()).map(gameReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getTvSeriesReviews(UUID id, Pageable pageable) {
        Page<TvSeriesReview> tvSeriesReview = tvSeriesReviewService.findAllByTvSeriesId(id, pageable);
        List<TvSeriesReview> tvSeriesReviews = tvSeriesReview.stream().sorted(Comparator.comparingInt(tvSeriesReviewLikeService::getLike)).collect(Collectors.toList());
        return new PageImpl<>(tvSeriesReviews, pageable, tvSeriesReviews.size()).map(tvSeriesReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getAnimeReviewsDesc(UUID id, Pageable pageable) {
        Page<AnimeReview> animeReview = animeReviewService.findAllByAnimeId(id, pageable);
        List<AnimeReview> animeReviews = animeReview.stream().sorted(Comparator.comparingInt(animeReviewLikeService::getLike).reversed()).collect(Collectors.toList());
        return new PageImpl<>(animeReviews, pageable, animeReviews.size()).map(animeReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getMangaReviewsDesc(UUID id, Pageable pageable) {
        Page<MangaReview> mangaReview = mangaReviewService.findAllByMangaId(id, pageable);
        List<MangaReview> mangaReviews = mangaReview.stream().sorted(Comparator.comparingInt(mangaReviewLikeService::getLike).reversed()).collect(Collectors.toList());
        return new PageImpl<>(mangaReviews, pageable, mangaReviews.size()).map(mangaReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getMovieReviewsDesc(UUID id, Pageable pageable) {
        Page<MovieReview> movieReview = movieReviewService.findAllByMovieId(id, pageable);
        List<MovieReview> movieReviews = movieReview.stream().sorted(Comparator.comparingInt(movieReviewLikeService::getLike).reversed()).collect(Collectors.toList());
        return new PageImpl<>(movieReviews, pageable, movieReviews.size()).map(movieReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getGameReviewsDesc(UUID id, Pageable pageable) {
        Page<GameReview> gameReview = gameReviewService.findAllByGameId(id, pageable);
        List<GameReview> gameReviews = gameReview.stream().sorted(Comparator.comparingInt(gameReviewLikeService::getLike).reversed()).collect(Collectors.toList());
        return new PageImpl<>(gameReviews, pageable, gameReviews.size()).map(gameReviewMapper::toReviewResponseDTO);
    }

    public Page<ReviewResponseDTO> getTvSeriesReviewsDesc(UUID id, Pageable pageable) {
        Page<TvSeriesReview> tvSeriesReview = tvSeriesReviewService.findAllByTvSeriesId(id, pageable);
        List<TvSeriesReview> tvSeriesReviews = tvSeriesReview.stream().sorted(Comparator.comparingInt(tvSeriesReviewLikeService::getLike).reversed()).collect(Collectors.toList());
        return new PageImpl<>(tvSeriesReviews, pageable, tvSeriesReviews.size()).map(tvSeriesReviewMapper::toReviewResponseDTO);
    }

}
