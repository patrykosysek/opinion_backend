package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class WorkOfCultureManagingService {
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final GameService gameService;
    private final TvSeriesService tvSeriesService;
    private final MovieService movieService;

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

}
