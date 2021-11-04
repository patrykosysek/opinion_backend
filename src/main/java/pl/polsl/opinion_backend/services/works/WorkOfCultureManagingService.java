package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.*;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.util.*;
import java.util.stream.Collectors;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class WorkOfCultureManagingService {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final GameService gameService;
    private final TvSeriesService tvSeriesService;
    private final MovieService movieService;

    private final AnimeMapper animeMapper;
    private final MangaMapper mangaMapper;
    private final GameMapper gameMapper;
    private final MovieMapper movieMapper;
    private final TvSeriesMapper tvSeriesMapper;

    public void delete(WorkOfCultureType workOfCultureType, UUID id) {

        switch (workOfCultureType) {
            case ANIME:
                animeService.delete(id);
                break;
            case MANGA:
                mangaService.delete(id);
                break;
            case MOVIE:
                movieService.delete(id);
                break;
            case TVSERIES:
                tvSeriesService.delete(id);
                break;
            case GAME:
                gameService.delete(id);
                break;
            default:
                throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);

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

    private Page<WorkOfCultureResponseDTO> getAnimeRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Anime> animeSet = genreType.isPresent() ? animeService.getAllByGenreName(genreType.get().name()) : animeService.getAll();
        List<Anime> animeList = animeSet.stream().sorted(Comparator.comparingDouble(Anime::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(animeList, pageable, animeList.size()).map(animeMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getMangaRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Manga> mangaSet = genreType.isPresent() ? mangaService.getAllByGenreName(genreType.get().name()) : mangaService.getAll();
        List<Manga> mangaList = mangaSet.stream().sorted(Comparator.comparingDouble(Manga::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(mangaList, pageable, mangaList.size()).map(mangaMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getMovieRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Movie> movieSet = genreType.isPresent() ? movieService.getAllByGenreName(genreType.get().name()) : movieService.getAll();
        List<Movie> movieList = movieSet.stream().sorted(Comparator.comparingDouble(Movie::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(movieList, pageable, movieList.size()).map(movieMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getTvSeriesRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<TvSeries> tvSeriesSet = genreType.isPresent() ? tvSeriesService.getAllByGenreName(genreType.get().name()) : tvSeriesService.getAll();
        List<TvSeries> tvSeriesList = tvSeriesSet.stream().sorted(Comparator.comparingDouble(TvSeries::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(tvSeriesList, pageable, tvSeriesList.size()).map(tvSeriesMapper::toWorkOfCultureResponseDTO);
    }

    private Page<WorkOfCultureResponseDTO> getGameRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Game> gameSet = genreType.isPresent() ? gameService.getAllByGenreName(genreType.get().name()) : gameService.getAll();
        List<Game> gameList = gameSet.stream().sorted(Comparator.comparingDouble(Game::workOfCultureInterest).reversed()).collect(Collectors.toList());
        return new PageImpl<>(gameList, pageable, gameList.size()).map(gameMapper::toWorkOfCultureResponseDTO);
    }

}
