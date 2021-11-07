package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.*;
import pl.polsl.opinion_backend.entities.user.Preference;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.*;
import pl.polsl.opinion_backend.services.list.review.*;
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
            animeSet.remove(animeSet.stream().filter(a -> a.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Anime> animeList = animeSet.stream().sorted(Comparator.comparingDouble(Anime::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(animeList, pageable, animeList.size()).map(animeMapper::toWorkOfCultureResponseDTO);

        } else return getAnimeRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getMangaRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Manga> mangaSet = mangaService.getAllByGenreName(preference.getFavouriteGenre().getName());
            mangaSet.remove(mangaSet.stream().filter(m -> m.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Manga> mangaList = mangaSet.stream().sorted(Comparator.comparingDouble(Manga::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(mangaList, pageable, mangaList.size()).map(mangaMapper::toWorkOfCultureResponseDTO);
        } else return getMangaRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getMovieRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Movie> movieSet = movieService.getAllByGenreName(preference.getFavouriteGenre().getName());
            movieSet.remove(movieSet.stream().filter(m -> m.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Movie> movieList = movieSet.stream().sorted(Comparator.comparingDouble(Movie::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(movieList, pageable, movieList.size()).map(movieMapper::toWorkOfCultureResponseDTO);
        } else return getMovieRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getGameRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<Game> gameSet = gameService.getAllByGenreName(preference.getFavouriteGenre().getName());
            gameSet.remove(gameSet.stream().filter(g -> g.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
            List<Game> gameList = gameSet.stream().sorted(Comparator.comparingDouble(Game::workOfCultureInterest).reversed()).collect(Collectors.toList());
            return new PageImpl<>(gameList, pageable, gameList.size()).map(gameMapper::toWorkOfCultureResponseDTO);
        } else return getGameRecommendation(Optional.empty(), pageable);
    }

    private Page<WorkOfCultureResponseDTO> getTvSeriesRecommendationByPreference(Preference preference, Pageable pageable) {
        if (preference != null) {
            Set<TvSeries> tvSeriesSet = tvSeriesService.getAllByGenreName(preference.getFavouriteGenre().getName());
            tvSeriesSet.remove(tvSeriesSet.stream().filter(t -> t.getTitle().equals(preference.getFavouriteTitle())).findFirst().orElse(null));
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

    public WorkOfCultureTimeStatisticResponseDTO getWorkOfCultureTimeStatistic(WorkOfCultureType workOfCultureType, UUID id, TimeDurationDTO timeDurationDTO) {
//        return switch (workOfCultureType) {
//            case ANIME -> getAnimeTimeStatistic(id, timeDurationDTO);
//            case MANGA -> mangaService.getStatistic(id);
//            case MOVIE -> movieService.getStatistic(id);
//            case TVSERIES -> tvSeriesService.getStatistic(id);
//            case GAME -> gameService.getStatistic(id);
//        };
        return getAnimeTimeStatistic(id, timeDurationDTO);
    }


    public WorkOfCultureTimeStatisticResponseDTO getAnimeTimeStatistic(UUID id, TimeDurationDTO timeDurationDTO) {

        Set<AnimeReview> animeReviewsBetween = animeReviewService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeReview> animeReviewsBefore = animeReviewService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        Set<AnimeDiscussion> animeDiscussionsBetween = animeDiscussionService.findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC), OffsetDateTime.of(timeDurationDTO.getEndDate(), LocalTime.NOON, ZoneOffset.UTC));
        Set<AnimeDiscussion> animeDiscussionsBefore = animeDiscussionService.findAllByAnimeIdAndCreateDateIsBefore(id, OffsetDateTime.of(timeDurationDTO.getStartDate(), LocalTime.NOON, ZoneOffset.UTC));

        WorkOfCultureTimeStatisticResponseDTO workOfCultureTimeStatisticResponseDTO = new WorkOfCultureTimeStatisticResponseDTO();
        workOfCultureTimeStatisticResponseDTO.setReviewCountBefore(animeReviewsBefore.size());
        workOfCultureTimeStatisticResponseDTO.setReviewCountGain(animeReviewsBetween.size());

        workOfCultureTimeStatisticResponseDTO.setDiscussionCountBefore(animeDiscussionsBefore.size());
        workOfCultureTimeStatisticResponseDTO.setDiscussionCountGain(animeDiscussionsBetween.size());

        return workOfCultureTimeStatisticResponseDTO;
    }


}
