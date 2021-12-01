package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.*;
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
import pl.polsl.opinion_backend.entities.user.Preference;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.*;
import pl.polsl.opinion_backend.services.list.review.*;
import pl.polsl.opinion_backend.services.list.seen.*;
import pl.polsl.opinion_backend.services.list.watch.*;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.anime.AnimeDiscussionService;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameDiscussionService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.genre.AnimeMangaGenreService;
import pl.polsl.opinion_backend.services.works.genre.GameGenreService;
import pl.polsl.opinion_backend.services.works.genre.MovieTvSeriesGenreService;
import pl.polsl.opinion_backend.services.works.manga.MangaDiscussionService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieDiscussionService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesDiscussionService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_TYPE_REQUIRED;

@RequiredArgsConstructor
@Service
@Slf4j
public class WorkOfCultureManagingService {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final GameService gameService;
    private final TvSeriesService tvSeriesService;
    private final MovieService movieService;

    private final AnimeReviewService animeReviewService;
    private final MangaReviewService mangaReviewService;
    private final MovieReviewService movieReviewService;
    private final TvSeriesReviewService tvSeriesReviewService;
    private final GameReviewService gameReviewService;

    private final AnimeDiscussionService animeDiscussionService;
    private final MangaDiscussionService mangaDiscussionService;
    private final MovieDiscussionService movieDiscussionService;
    private final TvSeriesDiscussionService tvSeriesDiscussionService;
    private final GameDiscussionService gameDiscussionService;

    private final AnimeWatchListService animeWatchListService;
    private final MangaWatchListService mangaWatchListService;
    private final MovieWatchListService movieWatchListService;
    private final TvSeriesWatchListService tvSeriesWatchListService;
    private final GameWatchListService gameWatchListService;

    private final AnimeSeenListService animeSeenListService;
    private final MangaSeenListService mangaSeenListService;
    private final MovieSeenListService movieSeenListService;
    private final TvSeriesSeenListService tvSeriesSeenListService;
    private final GameSeenListService gameSeenListService;

    private final AnimeMapper animeMapper;
    private final MangaMapper mangaMapper;
    private final GameMapper gameMapper;
    private final MovieMapper movieMapper;
    private final TvSeriesMapper tvSeriesMapper;

    private final AnimeMangaGenreService animeMangaGenreService;
    private final MovieTvSeriesGenreService movieTvSeriesGenreService;
    private final GameGenreService gameGenreService;

    private final UserService userService;

    public void delete(WorkOfCultureType workOfCultureType, UUID id) {

        switch (workOfCultureType) {
            case ANIME -> animeService.delete(id);
            case MANGA -> mangaService.delete(id);
            case MOVIE -> movieService.delete(id);
            case TVSERIES -> tvSeriesService.delete(id);
            case GAME -> gameService.delete(id);
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }
    }

    public Page<WorkOfCultureResponseDTO> getRecommendation(Optional<GenreType> genreType, WorkOfCultureType workOfCultureType, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> getAnimeRecommendation(genreType, pageable);
            case MANGA -> getMangaRecommendation(genreType, pageable);
            case MOVIE -> getMovieRecommendation(genreType, pageable);
            case TVSERIES -> getTvSeriesRecommendation(genreType, pageable);
            case GAME -> getGameRecommendation(genreType, pageable);
        };

    }

    public Page<WorkOfCultureResponseDTO> getRecommendationByPreference(WorkOfCultureType workOfCultureType, Pageable pageable) {
        User user = userService.getById(userService.getCurrentUser().getId());

        return switch (workOfCultureType) {
            case ANIME -> getAnimeRecommendationByPreference(user.getPreferenceOf(workOfCultureType), pageable);
            case MANGA -> getMangaRecommendationByPreference(user.getPreferenceOf(workOfCultureType), pageable);
            case MOVIE -> getMovieRecommendationByPreference(user.getPreferenceOf(workOfCultureType), pageable);
            case TVSERIES -> getTvSeriesRecommendationByPreference(user.getPreferenceOf(workOfCultureType), pageable);
            case GAME -> getGameRecommendationByPreference(user.getPreferenceOf(workOfCultureType), pageable);
        };

    }


    private Page<WorkOfCultureResponseDTO> getAnimeRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Anime> animeSet = genreType.isPresent() ? animeService.getAllByGenreName(genreType.get().getName()) : animeService.getAll();
        List<Anime> animeList = animeSet.stream().sorted(Comparator.comparingDouble(Anime::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(animeList, pageable, animeList.size()).map(animeMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getMangaRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Manga> mangaSet = genreType.isPresent() ? mangaService.getAllByGenreName(genreType.get().getName()) : mangaService.getAll();
        List<Manga> mangaList = mangaSet.stream().sorted(Comparator.comparingDouble(Manga::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(mangaList, pageable, mangaList.size()).map(mangaMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getMovieRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Movie> movieSet = genreType.isPresent() ? movieService.getAllByGenreName(genreType.get().getName()) : movieService.getAll();
        List<Movie> movieList = movieSet.stream().sorted(Comparator.comparingDouble(Movie::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(movieList, pageable, movieList.size()).map(movieMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getTvSeriesRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<TvSeries> tvSeriesSet = genreType.isPresent() ? tvSeriesService.getAllByGenreName(genreType.get().getName()) : tvSeriesService.getAll();
        List<TvSeries> tvSeriesList = tvSeriesSet.stream().sorted(Comparator.comparingDouble(TvSeries::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(tvSeriesList, pageable, tvSeriesList.size()).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getGameRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Game> gameSet = genreType.isPresent() ? gameService.getAllByGenreName(genreType.get().getName()) : gameService.getAll();
        List<Game> gameList = gameSet.stream().sorted(Comparator.comparingDouble(Game::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(gameList, pageable, gameList.size()).map(gameMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getAnimeRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Anime> animeSet = animeService.getAllByGenreName(preference.getFavouriteGenre().getName());
//            animeSet.remove(animeSet.stream().filter(a -> a.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Anime> animeList = animeSet.stream().sorted(Comparator.comparingDouble(Anime::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(animeList, pageable, animeList.size()).map(animeMapper::toWorkOfCultureResponseDTO);

        } else return getAnimeRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getMangaRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Manga> mangaSet = mangaService.getAllByGenreName(preference.getFavouriteGenre().getName());
//            mangaSet.remove(mangaSet.stream().filter(m -> m.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Manga> mangaList = mangaSet.stream().sorted(Comparator.comparingDouble(Manga::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(mangaList, pageable, mangaList.size()).map(mangaMapper::toWorkOfCultureResponseDTO);
        } else return getMangaRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getMovieRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Movie> movieSet = movieService.getAllByGenreName(preference.getFavouriteGenre().getName());
//            movieSet.remove(movieSet.stream().filter(m -> m.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Movie> movieList = movieSet.stream().sorted(Comparator.comparingDouble(Movie::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(movieList, pageable, movieList.size()).map(movieMapper::toWorkOfCultureResponseDTO);
        } else return getMovieRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getGameRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Game> gameSet = gameService.getAllByGenreName(preference.getFavouriteGenre().getName());
//            gameSet.remove(gameSet.stream().filter(g -> g.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Game> gameList = gameSet.stream().sorted(Comparator.comparingDouble(Game::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(gameList, pageable, gameList.size()).map(gameMapper::toWorkOfCultureResponseDTO);
        } else return getGameRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getTvSeriesRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<TvSeries> tvSeriesSet = tvSeriesService.getAllByGenreName(preference.getFavouriteGenre().getName());
//            tvSeriesSet.remove(tvSeriesSet.stream().filter(t -> t.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<TvSeries> tvSeriesList = tvSeriesSet.stream().sorted(Comparator.comparingDouble(TvSeries::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(tvSeriesList, pageable, tvSeriesList.size()).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
        } else return getTvSeriesRecommendation(Optional.empty(), pageable);
    }

    public Set<WorkGenreResponseDTO> getWorksOfCultureGenres() {

        Set<WorkGenreResponseDTO> workGenreResponseDTOSet = new HashSet<>();

        for (WorkOfCultureType w : WorkOfCultureType.values()) {

            switch (w) {
                case ANIME, MANGA -> workGenreResponseDTOSet.add(new WorkGenreResponseDTO(w, animeMangaGenreService.getAllGenres()));
                case MOVIE, TVSERIES -> workGenreResponseDTOSet.add(new WorkGenreResponseDTO(w, movieTvSeriesGenreService.getAllGenres()));
                case GAME -> workGenreResponseDTOSet.add(new WorkGenreResponseDTO(w, gameGenreService.getAllGenres()));
                default -> throw new IllegalArgumentException(WORK_OF_CULTURE_TYPE_REQUIRED);
            }

        }

        return workGenreResponseDTOSet;
    }

    public Set<GenreType> getAllWorksOfCultureGenres() {
        return Arrays.stream(GenreType.values()).collect(Collectors.toSet());
    }


    public Page<WorkOfCultureResponseDTO> getWorkOfCultureFilteredByTitle(String title, WorkOfCultureType workOfCultureType, Pageable pageable) {
        return switch (workOfCultureType) {
            case ANIME -> animeService.getAllFilteredByTitle(title, pageable).map(animeMapper::toWorkOfCultureResponseDTO);
            case MANGA -> mangaService.getAllFilteredByTitle(title, pageable).map(mangaMapper::toWorkOfCultureResponseDTO);
            case MOVIE -> movieService.getAllFilteredByTitle(title, pageable).map(movieMapper::toWorkOfCultureResponseDTO);
            case TVSERIES -> tvSeriesService.getAllFilteredByTitle(title, pageable).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
            case GAME -> gameService.getAllFilteredByTitle(title, pageable).map(gameMapper::toWorkOfCultureResponseDTO);
        };
    }

    public Page<WorkOfCultureResponseDTO> getAllWorkOfCulture(WorkOfCultureType workOfCultureType, Pageable pageable) {
        return switch (workOfCultureType) {
            case ANIME -> animeService.findAll(pageable).map(animeMapper::toWorkOfCultureResponseDTO);
            case MANGA -> mangaService.findAll(pageable).map(mangaMapper::toWorkOfCultureResponseDTO);
            case MOVIE -> movieService.findAll(pageable).map(movieMapper::toWorkOfCultureResponseDTO);
            case TVSERIES -> tvSeriesService.findAll(pageable).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
            case GAME -> gameService.findAll(pageable).map(gameMapper::toWorkOfCultureResponseDTO);
        };
    }

    public WorkOfCultureStatisticResponseDTO getWorkOfCultureStatistic(WorkOfCultureType workOfCultureType, UUID id) {
        return switch (workOfCultureType) {
            case ANIME -> animeService.getStatistic(id);
            case MANGA -> mangaService.getStatistic(id);
            case MOVIE -> movieService.getStatistic(id);
            case TVSERIES -> tvSeriesService.getStatistic(id);
            case GAME -> gameService.getStatistic(id);
        };
    }

    public StatisticTimeResponseDTO getWorkOfCultureTimeStatistic(WorkOfCultureType workOfCultureType, UUID id, TimeDurationDTO timeDurationDTO) {
        return switch (workOfCultureType) {
            case ANIME -> getAnimeTimeStatistic(id, timeDurationDTO);
            case MANGA -> getMangaTimeStatistic(id, timeDurationDTO);
            case MOVIE -> getMovieTimeStatistic(id, timeDurationDTO);
            case TVSERIES -> getTvSeriesTimeStatistic(id, timeDurationDTO);
            case GAME -> getGameTimeStatistic(id, timeDurationDTO);
        };
    }

    public StatisticTimeResponseDTO getAnimeTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<AnimeReview> animeReviewsBetween = animeReviewService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeReview> animeReviewsBefore = animeReviewService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<AnimeDiscussion> animeDiscussionsBetween = animeDiscussionService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeDiscussion> animeDiscussionsBefore = animeDiscussionService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<AnimeSeenList> animeSeenListsBetween = animeSeenListService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeSeenList> animeSeenListsBefore = animeSeenListService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<AnimeWatchList> animeWatchListsBetween = animeWatchListService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeWatchList> animeWatchListsBefore = animeWatchListService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        int reviewCountBefore = animeReviewsBefore.size();
        int reviewCountGain = animeReviewsBetween.size();
        int reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : 100;

        int discussionCountBefore = animeDiscussionsBefore.size();
        int discussionCountGain = animeDiscussionsBetween.size();
        int discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : 100;

        int watchListCountBefore = animeWatchListsBefore.size();
        int watchListCountGain = animeWatchListsBetween.size();
        int watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : 100;

        int seenListCountBefore = animeSeenListsBefore.size();
        int seenListCountGain = animeSeenListsBetween.size();
        int seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : 100;


        StatisticTimeResponseDTO statisticTimeResponseDTO = new StatisticTimeResponseDTO();
        statisticTimeResponseDTO.setReviewCountBefore(reviewCountBefore);
        statisticTimeResponseDTO.setReviewCountGain(reviewCountGain);
        statisticTimeResponseDTO.setReviewCountGainPercent(reviewCountGainPercent);

        statisticTimeResponseDTO.setDiscussionCountBefore(discussionCountBefore);
        statisticTimeResponseDTO.setDiscussionCountGain(discussionCountGain);
        statisticTimeResponseDTO.setDiscussionCountGainPercent(discussionCountGainPercent);

        statisticTimeResponseDTO.setWatchListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setWatchListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setWatchListCountGainPercent(watchListCountGainPercent);

        statisticTimeResponseDTO.setSeenListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setSeenListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setSeenListCountGainPercent(seenListCountGainPercent);

        return statisticTimeResponseDTO;
    }

    public StatisticTimeResponseDTO getMangaTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<MangaReview> mangaReviewsBetween = mangaReviewService.findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MangaReview> mangaReviewsBefore = mangaReviewService.findAllByMangaIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MangaDiscussion> mangaDiscussionsBetween = mangaDiscussionService.findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MangaDiscussion> mangaDiscussionsBefore = mangaDiscussionService.findAllByMangaIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MangaSeenList> mangaSeenListsBetween = mangaSeenListService.findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MangaSeenList> mangaSeenListsBefore = mangaSeenListService.findAllByMangaIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MangaWatchList> mangaWatchListsBetween = mangaWatchListService.findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MangaWatchList> magaWatchListsBefore = mangaWatchListService.findAllByMangaIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        int reviewCountBefore = mangaReviewsBefore.size();
        int reviewCountGain = mangaReviewsBetween.size();
        int reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : 100;

        int discussionCountBefore = mangaDiscussionsBefore.size();
        int discussionCountGain = mangaDiscussionsBetween.size();
        int discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : 100;

        int watchListCountBefore = magaWatchListsBefore.size();
        int watchListCountGain = mangaWatchListsBetween.size();
        int watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : 100;

        int seenListCountBefore = mangaSeenListsBefore.size();
        int seenListCountGain = mangaSeenListsBetween.size();
        int seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : 100;


        StatisticTimeResponseDTO statisticTimeResponseDTO = new StatisticTimeResponseDTO();
        statisticTimeResponseDTO.setReviewCountBefore(reviewCountBefore);
        statisticTimeResponseDTO.setReviewCountGain(reviewCountGain);
        statisticTimeResponseDTO.setReviewCountGainPercent(reviewCountGainPercent);

        statisticTimeResponseDTO.setDiscussionCountBefore(discussionCountBefore);
        statisticTimeResponseDTO.setDiscussionCountGain(discussionCountGain);
        statisticTimeResponseDTO.setDiscussionCountGainPercent(discussionCountGainPercent);

        statisticTimeResponseDTO.setWatchListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setWatchListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setWatchListCountGainPercent(watchListCountGainPercent);

        statisticTimeResponseDTO.setSeenListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setSeenListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setSeenListCountGainPercent(seenListCountGainPercent);

        return statisticTimeResponseDTO;
    }

    public StatisticTimeResponseDTO getMovieTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<MovieReview> movieReviewsBetween = movieReviewService.findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MovieReview> movieReviewsBefore = movieReviewService.findAllByMovieIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MovieDiscussion> movieDiscussionsBetween = movieDiscussionService.findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MovieDiscussion> movieDiscussionsBefore = movieDiscussionService.findAllByMovieIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MovieSeenList> movieSeenListsBetween = movieSeenListService.findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MovieSeenList> movieSeenListsBefore = movieSeenListService.findAllByMovieIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<MovieWatchList> movieWatchListsBetween = movieWatchListService.findAllByMovieIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<MovieWatchList> movieWatchListsBefore = movieWatchListService.findAllByMovieIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        int reviewCountBefore = movieReviewsBefore.size();
        int reviewCountGain = movieReviewsBetween.size();
        int reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : 100;

        int discussionCountBefore = movieDiscussionsBefore.size();
        int discussionCountGain = movieDiscussionsBetween.size();
        int discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : 100;

        int watchListCountBefore = movieWatchListsBefore.size();
        int watchListCountGain = movieWatchListsBetween.size();
        int watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : 100;

        int seenListCountBefore = movieSeenListsBefore.size();
        int seenListCountGain = movieSeenListsBetween.size();
        int seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : 100;


        StatisticTimeResponseDTO statisticTimeResponseDTO = new StatisticTimeResponseDTO();
        statisticTimeResponseDTO.setReviewCountBefore(reviewCountBefore);
        statisticTimeResponseDTO.setReviewCountGain(reviewCountGain);
        statisticTimeResponseDTO.setReviewCountGainPercent(reviewCountGainPercent);

        statisticTimeResponseDTO.setDiscussionCountBefore(discussionCountBefore);
        statisticTimeResponseDTO.setDiscussionCountGain(discussionCountGain);
        statisticTimeResponseDTO.setDiscussionCountGainPercent(discussionCountGainPercent);

        statisticTimeResponseDTO.setWatchListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setWatchListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setWatchListCountGainPercent(watchListCountGainPercent);

        statisticTimeResponseDTO.setSeenListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setSeenListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setSeenListCountGainPercent(seenListCountGainPercent);

        return statisticTimeResponseDTO;
    }

    public StatisticTimeResponseDTO getTvSeriesTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<TvSeriesReview> tvSeriesReviewsBetween = tvSeriesReviewService.findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<TvSeriesReview> tvSeriesReviewsBefore = tvSeriesReviewService.findAllByTvSeriesIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<TvSeriesDiscussion> tvSeriesDiscussionsBetween = tvSeriesDiscussionService.findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<TvSeriesDiscussion> tvSeriesDiscussionsBefore = tvSeriesDiscussionService.findAllByTvSeriesIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<TvSeriesSeenList> tvSeriesSeenListsBetween = tvSeriesSeenListService.findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<TvSeriesSeenList> tvSeriesSeenListsBefore = tvSeriesSeenListService.findAllByTvSeriesIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<TvSeriesWatchList> tvSeriesWatchListsBetween = tvSeriesWatchListService.findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<TvSeriesWatchList> tvSeriesWatchListsBefore = tvSeriesWatchListService.findAllByTvSeriesIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        int reviewCountBefore = tvSeriesReviewsBefore.size();
        int reviewCountGain = tvSeriesReviewsBetween.size();
        int reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : 100;

        int discussionCountBefore = tvSeriesDiscussionsBefore.size();
        int discussionCountGain = tvSeriesDiscussionsBetween.size();
        int discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : 100;

        int watchListCountBefore = tvSeriesWatchListsBefore.size();
        int watchListCountGain = tvSeriesWatchListsBetween.size();
        int watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : 100;

        int seenListCountBefore = tvSeriesSeenListsBefore.size();
        int seenListCountGain = tvSeriesSeenListsBetween.size();
        int seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : 100;


        StatisticTimeResponseDTO statisticTimeResponseDTO = new StatisticTimeResponseDTO();
        statisticTimeResponseDTO.setReviewCountBefore(reviewCountBefore);
        statisticTimeResponseDTO.setReviewCountGain(reviewCountGain);
        statisticTimeResponseDTO.setReviewCountGainPercent(reviewCountGainPercent);

        statisticTimeResponseDTO.setDiscussionCountBefore(discussionCountBefore);
        statisticTimeResponseDTO.setDiscussionCountGain(discussionCountGain);
        statisticTimeResponseDTO.setDiscussionCountGainPercent(discussionCountGainPercent);

        statisticTimeResponseDTO.setWatchListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setWatchListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setWatchListCountGainPercent(watchListCountGainPercent);

        statisticTimeResponseDTO.setSeenListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setSeenListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setSeenListCountGainPercent(seenListCountGainPercent);

        return statisticTimeResponseDTO;
    }

    public StatisticTimeResponseDTO getGameTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<GameReview> gameReviewsBetween = gameReviewService.findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<GameReview> gameReviewsBefore = gameReviewService.findAllByGameIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<GameDiscussion> gameDiscussionsBetween = gameDiscussionService.findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<GameDiscussion> gameDiscussionsBefore = gameDiscussionService.findAllByGameIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<GameSeenList> gameSeenListsBetween = gameSeenListService.findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<GameSeenList> gameSeenListsBefore = gameSeenListService.findAllByGameIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<GameWatchList> gameWatchListsBetween = gameWatchListService.findAllByGameIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<GameWatchList> gameWatchListsBefore = gameWatchListService.findAllByGameIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        int reviewCountBefore = gameReviewsBefore.size();
        int reviewCountGain = gameReviewsBetween.size();
        int reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;

        int discussionCountBefore = gameDiscussionsBefore.size();
        int discussionCountGain = gameDiscussionsBetween.size();
        int discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

        int watchListCountBefore = gameWatchListsBefore.size();
        int watchListCountGain = gameWatchListsBetween.size();
        int watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

        int seenListCountBefore = gameSeenListsBefore.size();
        int seenListCountGain = gameSeenListsBetween.size();
        int seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;


        StatisticTimeResponseDTO statisticTimeResponseDTO = new StatisticTimeResponseDTO();
        statisticTimeResponseDTO.setReviewCountBefore(reviewCountBefore);
        statisticTimeResponseDTO.setReviewCountGain(reviewCountGain);
        statisticTimeResponseDTO.setReviewCountGainPercent(reviewCountGainPercent);

        statisticTimeResponseDTO.setDiscussionCountBefore(discussionCountBefore);
        statisticTimeResponseDTO.setDiscussionCountGain(discussionCountGain);
        statisticTimeResponseDTO.setDiscussionCountGainPercent(discussionCountGainPercent);

        statisticTimeResponseDTO.setWatchListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setWatchListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setWatchListCountGainPercent(watchListCountGainPercent);

        statisticTimeResponseDTO.setSeenListCountBefore(seenListCountBefore);
        statisticTimeResponseDTO.setSeenListCountGain(seenListCountGain);
        statisticTimeResponseDTO.setSeenListCountGainPercent(seenListCountGainPercent);

        return statisticTimeResponseDTO;
    }

    public GlobalStatisticResponseDTO getWorkOfCultureOverallStatistic(TimeDurationDTO timeDurationDTO) {

        Set<GenreType> animeMangaGenres = animeMangaGenreService.getAllGenres();
        Set<GenreType> movieTvSeriesGenres = movieTvSeriesGenreService.getAllGenres();
        Set<GenreType> gameGenres = gameGenreService.getAllGenres();

        GlobalStatisticResponseDTO statistic = new GlobalStatisticResponseDTO();


        for (WorkOfCultureType w : WorkOfCultureType.values()) {
            StatisticTypeResponseDTO statisticTypeResponseDTO = new StatisticTypeResponseDTO();
            statisticTypeResponseDTO.setWorkOfCultureType(w);
            switch (w) {
                case ANIME -> statisticTypeResponseDTO.setGenreStatistic(getAnimeTimeStatisticByGenre(animeMangaGenres, timeDurationDTO));
                case MANGA -> statisticTypeResponseDTO.setGenreStatistic(getMangaTimeStatisticByGenre(animeMangaGenres, timeDurationDTO));
                case MOVIE -> statisticTypeResponseDTO.setGenreStatistic(getMovieTimeStatisticByGenre(movieTvSeriesGenres, timeDurationDTO));
                case TVSERIES -> statisticTypeResponseDTO.setGenreStatistic(getTvSeriesTimeStatisticByGenre(movieTvSeriesGenres, timeDurationDTO));
                case GAME -> statisticTypeResponseDTO.setGenreStatistic(getGameTimeStatisticByGenre(gameGenres, timeDurationDTO));
            }
            statistic.getStatistic().add(statisticTypeResponseDTO);
        }

        return statistic;
    }

    public Set<StatisticGenreResponseDTO> getAnimeTimeStatisticByGenre(Set<GenreType> genres, TimeDurationDTO timeDurationDTO) {

        Set<StatisticGenreResponseDTO> statistic = new HashSet<>();

        OffsetDateTime startDate = OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endDate = OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC);

        for (GenreType genreType : genres) {

            int reviewCount;
            int discussionCount;
            int watchListCount;
            int seenListCount;

            int reviewCountBefore;
            int reviewCountGain;
            int reviewCountGainPercent;

            int discussionCountBefore;
            int discussionCountGain;
            int discussionCountGainPercent;

            int watchListCountBefore;
            int watchListCountGain;
            int watchListCountGainPercent;

            int seenListCountBefore;
            int seenListCountGain;
            int seenListCountGainPercent;

            StatisticGenreResponseDTO genreStatistic = new StatisticGenreResponseDTO();
            genreStatistic.setGenreType(genreType);

            String genreName = genreType.getName();

            reviewCount = animeReviewService.findAllByGenresName(genreName).size();
            reviewCountBefore = animeReviewService.findAllByAnimeGenresAndCreateDateIsBefore(genreName, startDate).size();
            reviewCountGain = animeReviewService.findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;


            discussionCount = animeDiscussionService.findAllByGenresName(genreName).size();
            discussionCountBefore = animeDiscussionService.findAllByAnimeGenresAndCreateDateIsBefore(genreName, startDate).size();
            discussionCountGain = animeDiscussionService.findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

            watchListCount = animeWatchListService.findAllByGenresName(genreName).size();
            watchListCountBefore = animeWatchListService.findAllByAnimeGenresAndCreateDateIsBefore(genreName, startDate).size();
            watchListCountGain = animeWatchListService.findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

            seenListCount = animeSeenListService.findAllByGenresName(genreName).size();
            seenListCountBefore = animeSeenListService.findAllByAnimeGenresAndCreateDateIsBefore(genreName, startDate).size();
            seenListCountGain = animeSeenListService.findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;

            genreStatistic.setReviewCount(reviewCount);
            genreStatistic.setReviewCountBefore(reviewCountBefore);
            genreStatistic.setReviewCountGain(reviewCountGain);
            genreStatistic.setReviewCountGainPercent(reviewCountGainPercent);

            genreStatistic.setDiscussionCount(discussionCount);
            genreStatistic.setDiscussionCountBefore(discussionCountBefore);
            genreStatistic.setDiscussionCountGain(discussionCountGain);
            genreStatistic.setDiscussionCountGainPercent(discussionCountGainPercent);

            genreStatistic.setWatchListCount(watchListCount);
            genreStatistic.setWatchListCountBefore(watchListCountBefore);
            genreStatistic.setWatchListCountGain(watchListCountGain);
            genreStatistic.setWatchListCountGainPercent(watchListCountGainPercent);

            genreStatistic.setSeenListCount(seenListCount);
            genreStatistic.setSeenListCountBefore(seenListCountBefore);
            genreStatistic.setSeenListCountGain(seenListCountGain);
            genreStatistic.setSeenListCountGainPercent(seenListCountGainPercent);

            statistic.add(genreStatistic);
        }

        return statistic;
    }

    public Set<StatisticGenreResponseDTO> getMangaTimeStatisticByGenre(Set<GenreType> genres, TimeDurationDTO timeDurationDTO) {

        Set<StatisticGenreResponseDTO> statistic = new HashSet<>();

        OffsetDateTime startDate = OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endDate = OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC);

        for (GenreType genreType : genres) {

            int reviewCount;
            int discussionCount;
            int watchListCount;
            int seenListCount;

            int reviewCountBefore;
            int reviewCountGain;
            int reviewCountGainPercent;

            int discussionCountBefore;
            int discussionCountGain;
            int discussionCountGainPercent;

            int watchListCountBefore;
            int watchListCountGain;
            int watchListCountGainPercent;

            int seenListCountBefore;
            int seenListCountGain;
            int seenListCountGainPercent;

            StatisticGenreResponseDTO genreStatistic = new StatisticGenreResponseDTO();
            genreStatistic.setGenreType(genreType);

            String genreName = genreType.getName();

            reviewCount = mangaReviewService.findAllByGenresName(genreName).size();
            reviewCountBefore = mangaReviewService.findAllByMangaGenresAndCreateDateIsBefore(genreName, startDate).size();
            reviewCountGain = mangaReviewService.findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;

            discussionCount = mangaDiscussionService.findAllByGenresName(genreName).size();
            discussionCountBefore = mangaDiscussionService.findAllByMangaGenresAndCreateDateIsBefore(genreName, startDate).size();
            discussionCountGain = mangaDiscussionService.findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

            watchListCount = mangaWatchListService.findAllByGenresName(genreName).size();
            watchListCountBefore = mangaWatchListService.findAllByMangaGenresAndCreateDateIsBefore(genreName, startDate).size();
            watchListCountGain = mangaWatchListService.findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

            seenListCount = mangaSeenListService.findAllByGenresName(genreName).size();
            seenListCountBefore = mangaSeenListService.findAllByMangaGenresAndCreateDateIsBefore(genreName, startDate).size();
            seenListCountGain = mangaSeenListService.findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;

            genreStatistic.setReviewCount(reviewCount);
            genreStatistic.setReviewCountBefore(reviewCountBefore);
            genreStatistic.setReviewCountGain(reviewCountGain);
            genreStatistic.setReviewCountGainPercent(reviewCountGainPercent);

            genreStatistic.setDiscussionCount(discussionCount);
            genreStatistic.setDiscussionCountBefore(discussionCountBefore);
            genreStatistic.setDiscussionCountGain(discussionCountGain);
            genreStatistic.setDiscussionCountGainPercent(discussionCountGainPercent);

            genreStatistic.setWatchListCount(watchListCount);
            genreStatistic.setWatchListCountBefore(watchListCountBefore);
            genreStatistic.setWatchListCountGain(watchListCountGain);
            genreStatistic.setWatchListCountGainPercent(watchListCountGainPercent);

            genreStatistic.setSeenListCount(seenListCount);
            genreStatistic.setSeenListCountBefore(seenListCountBefore);
            genreStatistic.setSeenListCountGain(seenListCountGain);
            genreStatistic.setSeenListCountGainPercent(seenListCountGainPercent);

            statistic.add(genreStatistic);
        }

        return statistic;
    }

    public Set<StatisticGenreResponseDTO> getMovieTimeStatisticByGenre(Set<GenreType> genres, TimeDurationDTO timeDurationDTO) {

        Set<StatisticGenreResponseDTO> statistic = new HashSet<>();

        OffsetDateTime startDate = OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endDate = OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC);

        for (GenreType genreType : genres) {

            int reviewCount;
            int discussionCount;
            int watchListCount;
            int seenListCount;

            int reviewCountBefore;
            int reviewCountGain;
            int reviewCountGainPercent;

            int discussionCountBefore;
            int discussionCountGain;
            int discussionCountGainPercent;

            int watchListCountBefore;
            int watchListCountGain;
            int watchListCountGainPercent;

            int seenListCountBefore;
            int seenListCountGain;
            int seenListCountGainPercent;

            StatisticGenreResponseDTO genreStatistic = new StatisticGenreResponseDTO();
            genreStatistic.setGenreType(genreType);

            String genreName = genreType.getName();

            reviewCount = movieReviewService.findAllByGenresName(genreName).size();
            reviewCountBefore = movieReviewService.findAllByMovieGenresAndCreateDateIsBefore(genreName, startDate).size();
            reviewCountGain = movieReviewService.findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;

            discussionCount = movieDiscussionService.findAllByGenresName(genreName).size();
            discussionCountBefore = movieDiscussionService.findAllByMovieGenresAndCreateDateIsBefore(genreName, startDate).size();
            discussionCountGain = movieDiscussionService.findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

            watchListCount = movieWatchListService.findAllByGenresName(genreName).size();
            watchListCountBefore = movieWatchListService.findAllByMovieGenresAndCreateDateIsBefore(genreName, startDate).size();
            watchListCountGain = movieWatchListService.findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

            seenListCount = movieSeenListService.findAllByGenresName(genreName).size();
            seenListCountBefore = movieSeenListService.findAllByMovieGenresAndCreateDateIsBefore(genreName, startDate).size();
            seenListCountGain = movieSeenListService.findAllByMovieGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;

            genreStatistic.setReviewCount(reviewCount);
            genreStatistic.setReviewCountBefore(reviewCountBefore);
            genreStatistic.setReviewCountGain(reviewCountGain);
            genreStatistic.setReviewCountGainPercent(reviewCountGainPercent);

            genreStatistic.setDiscussionCount(discussionCount);
            genreStatistic.setDiscussionCountBefore(discussionCountBefore);
            genreStatistic.setDiscussionCountGain(discussionCountGain);
            genreStatistic.setDiscussionCountGainPercent(discussionCountGainPercent);

            genreStatistic.setWatchListCount(watchListCount);
            genreStatistic.setWatchListCountBefore(watchListCountBefore);
            genreStatistic.setWatchListCountGain(watchListCountGain);
            genreStatistic.setWatchListCountGainPercent(watchListCountGainPercent);

            genreStatistic.setSeenListCount(seenListCount);
            genreStatistic.setSeenListCountBefore(seenListCountBefore);
            genreStatistic.setSeenListCountGain(seenListCountGain);
            genreStatistic.setSeenListCountGainPercent(seenListCountGainPercent);

            statistic.add(genreStatistic);
        }

        return statistic;
    }

    public Set<StatisticGenreResponseDTO> getTvSeriesTimeStatisticByGenre(Set<GenreType> genres, TimeDurationDTO timeDurationDTO) {

        Set<StatisticGenreResponseDTO> statistic = new HashSet<>();

        OffsetDateTime startDate = OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endDate = OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC);

        for (GenreType genreType : genres) {

            int reviewCount;
            int discussionCount;
            int watchListCount;
            int seenListCount;

            int reviewCountBefore;
            int reviewCountGain;
            int reviewCountGainPercent;

            int discussionCountBefore;
            int discussionCountGain;
            int discussionCountGainPercent;

            int watchListCountBefore;
            int watchListCountGain;
            int watchListCountGainPercent;

            int seenListCountBefore;
            int seenListCountGain;
            int seenListCountGainPercent;

            StatisticGenreResponseDTO genreStatistic = new StatisticGenreResponseDTO();
            genreStatistic.setGenreType(genreType);

            String genreName = genreType.getName();

            reviewCount = tvSeriesReviewService.findAllByGenresName(genreName).size();
            reviewCountBefore = tvSeriesReviewService.findAllByTvSeriesGenresAndCreateDateIsBefore(genreName, startDate).size();
            reviewCountGain = tvSeriesReviewService.findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;

            discussionCount = tvSeriesDiscussionService.findAllByGenresName(genreName).size();
            discussionCountBefore = tvSeriesDiscussionService.findAllByTvSeriesGenresAndCreateDateIsBefore(genreName, startDate).size();
            discussionCountGain = tvSeriesDiscussionService.findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

            watchListCount = tvSeriesWatchListService.findAllByGenresName(genreName).size();
            watchListCountBefore = tvSeriesWatchListService.findAllByTvSeriesGenresAndCreateDateIsBefore(genreName, startDate).size();
            watchListCountGain = tvSeriesWatchListService.findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

            seenListCount = tvSeriesSeenListService.findAllByGenresName(genreName).size();
            seenListCountBefore = tvSeriesSeenListService.findAllByTvSeriesGenresAndCreateDateIsBefore(genreName, startDate).size();
            seenListCountGain = tvSeriesSeenListService.findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;

            genreStatistic.setReviewCount(reviewCount);
            genreStatistic.setReviewCountBefore(reviewCountBefore);
            genreStatistic.setReviewCountGain(reviewCountGain);
            genreStatistic.setReviewCountGainPercent(reviewCountGainPercent);

            genreStatistic.setDiscussionCount(discussionCount);
            genreStatistic.setDiscussionCountBefore(discussionCountBefore);
            genreStatistic.setDiscussionCountGain(discussionCountGain);
            genreStatistic.setDiscussionCountGainPercent(discussionCountGainPercent);

            genreStatistic.setWatchListCount(watchListCount);
            genreStatistic.setWatchListCountBefore(watchListCountBefore);
            genreStatistic.setWatchListCountGain(watchListCountGain);
            genreStatistic.setWatchListCountGainPercent(watchListCountGainPercent);

            genreStatistic.setSeenListCount(seenListCount);
            genreStatistic.setSeenListCountBefore(seenListCountBefore);
            genreStatistic.setSeenListCountGain(seenListCountGain);
            genreStatistic.setSeenListCountGainPercent(seenListCountGainPercent);

            statistic.add(genreStatistic);
        }

        return statistic;
    }

    public Set<StatisticGenreResponseDTO> getGameTimeStatisticByGenre(Set<GenreType> genres, TimeDurationDTO timeDurationDTO) {

        Set<StatisticGenreResponseDTO> statistic = new HashSet<>();

        OffsetDateTime startDate = OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime endDate = OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC);

        for (GenreType genreType : genres) {

            int reviewCount;
            int discussionCount;
            int watchListCount;
            int seenListCount;

            int reviewCountBefore;
            int reviewCountGain;
            int reviewCountGainPercent;

            int discussionCountBefore;
            int discussionCountGain;
            int discussionCountGainPercent;

            int watchListCountBefore;
            int watchListCountGain;
            int watchListCountGainPercent;

            int seenListCountBefore;
            int seenListCountGain;
            int seenListCountGainPercent;

            StatisticGenreResponseDTO genreStatistic = new StatisticGenreResponseDTO();
            genreStatistic.setGenreType(genreType);

            String genreName = genreType.getName();

            reviewCount = gameReviewService.findAllByGenresName(genreName).size();
            reviewCountBefore = gameReviewService.findAllByGameGenresNameAndCreateDateIsBefore(genreName, startDate).size();
            reviewCountGain = gameReviewService.findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            reviewCountGainPercent = reviewCountBefore != 0 ? reviewCountGain * 100 / reviewCountBefore : reviewCountGain == 0 ? 0 : 100;

            discussionCount = gameDiscussionService.findAllByGenresName(genreName).size();
            discussionCountBefore = gameDiscussionService.findAllByGameGenresAndCreateDateIsBefore(genreName, startDate).size();
            discussionCountGain = gameDiscussionService.findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            discussionCountGainPercent = discussionCountBefore != 0 ? discussionCountGain * 100 / discussionCountBefore : discussionCountGain == 0 ? 0 : 100;

            watchListCount = gameWatchListService.findAllByGenresName(genreName).size();
            watchListCountBefore = gameWatchListService.findAllByGameGenresAndCreateDateIsBefore(genreName, startDate).size();
            watchListCountGain = gameWatchListService.findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            watchListCountGainPercent = watchListCountBefore != 0 ? watchListCountGain * 100 / watchListCountBefore : watchListCountGain == 0 ? 0 : 100;

            seenListCount = gameSeenListService.findAllByGenresName(genreName).size();
            seenListCountBefore = gameSeenListService.findAllByGameGenresNameAndCreateDateIsBefore(genreName, startDate).size();
            seenListCountGain = gameSeenListService.findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(genreName, startDate, endDate).size();
            seenListCountGainPercent = seenListCountBefore != 0 ? seenListCountGain * 100 / seenListCountBefore : seenListCountGain == 0 ? 0 : 100;

            genreStatistic.setReviewCount(reviewCount);
            genreStatistic.setReviewCountBefore(reviewCountBefore);
            genreStatistic.setReviewCountGain(reviewCountGain);
            genreStatistic.setReviewCountGainPercent(reviewCountGainPercent);

            genreStatistic.setDiscussionCount(discussionCount);
            genreStatistic.setDiscussionCountBefore(discussionCountBefore);
            genreStatistic.setDiscussionCountGain(discussionCountGain);
            genreStatistic.setDiscussionCountGainPercent(discussionCountGainPercent);

            genreStatistic.setWatchListCount(watchListCount);
            genreStatistic.setWatchListCountBefore(watchListCountBefore);
            genreStatistic.setWatchListCountGain(watchListCountGain);
            genreStatistic.setWatchListCountGainPercent(watchListCountGainPercent);

            genreStatistic.setSeenListCount(seenListCount);
            genreStatistic.setSeenListCountBefore(seenListCountBefore);
            genreStatistic.setSeenListCountGain(seenListCountGain);
            genreStatistic.setSeenListCountGainPercent(seenListCountGainPercent);

            statistic.add(genreStatistic);
        }

        return statistic;
    }

    public Set<WorkOfCultureResponseDTO> getRecommendedWorks(Pageable pageable) {

        Set<WorkOfCultureResponseDTO> recommended = new HashSet<>();
        for (WorkOfCultureType w : WorkOfCultureType.values()) {
            recommended.add(getRecommendation(Optional.empty(), w, pageable).stream().findFirst().orElse(null));
        }
        return recommended;
    }

    public WorkOfCultureInformationResponseDTO getWorkOfCultureInformation(WorkOfCultureType workOfCultureType, UUID id) {

        return switch (workOfCultureType) {
            case ANIME -> animeMapper.toWorkOfCultureInformationResponseDTO(animeService.getById(id));
            case MANGA -> mangaMapper.toWorkOfCultureInformationResponseDTO(mangaService.getById(id));
            case MOVIE -> movieMapper.toWorkOfCultureInformationResponseDTO(movieService.getById(id));
            case TVSERIES -> tvSeriesMapper.toWorkOfCultureInformationResponseDTO(tvSeriesService.getById(id));
            case GAME -> gameMapper.toWorkOfCultureInformationResponseDTO(gameService.getById(id));
        };
    }
}
