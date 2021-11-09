package pl.polsl.opinion_backend.services.works.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.review.*;
import pl.polsl.opinion_backend.services.list.review.*;

import java.util.UUID;

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

    public ReviewResponseDTO getReviewInformation(WorkOfCultureType workOfCultureType, UUID id) {

        return switch (workOfCultureType) {
            case ANIME -> animeReviewMapper.toReviewResponseDTO(animeReviewService.getById(id));
            case MANGA -> mangaReviewMapper.toReviewResponseDTO(mangaReviewService.getById(id));
            case MOVIE -> movieReviewMapper.toReviewResponseDTO(movieReviewService.getById(id));
            case TVSERIES -> tvSeriesReviewMapper.toReviewResponseDTO(tvSeriesReviewService.getById(id));
            case GAME -> gameReviewMapper.toReviewResponseDTO(gameReviewService.getById(id));
        };

    }

}
