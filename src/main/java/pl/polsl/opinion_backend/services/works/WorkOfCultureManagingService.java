package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.AnimeMapper;
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

        switch (workOfCultureType) {
            case ANIME:
                return getAnimeRecommendation(genreType, pageable);
//            case MANGA:
//
//                break;
//            case MOVIE:
//
//                break;
//            case TVSERIES:
//
//                break;
//            case GAME:
//
//                break;
            default:
                throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }

    }

    private Page<WorkOfCultureResponseDTO> getAnimeRecommendation(Optional<GenreType> genreType, Pageable pageable) {
        Set<Anime> animePage = genreType.isPresent() ? animeService.getAllByGenreName(genreType.get().name()) : animeService.getAll();
        List<Anime> animeList = animePage.stream().sorted(Comparator.comparingDouble(Anime::workOfCultureInterest).reversed()).collect(Collectors.toList());

        return new PageImpl<>(animeList, pageable, animeList.size()).map(animeMapper::toWorkOfCultureResponseDTO);

    }

}
