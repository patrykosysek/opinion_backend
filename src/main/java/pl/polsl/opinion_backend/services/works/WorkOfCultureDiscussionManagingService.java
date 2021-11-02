package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.anime.AnimeDiscussionAnswerService;
import pl.polsl.opinion_backend.services.works.anime.AnimeDiscussionService;
import pl.polsl.opinion_backend.services.works.game.GameDiscussionAnswerService;
import pl.polsl.opinion_backend.services.works.game.GameDiscussionService;
import pl.polsl.opinion_backend.services.works.manga.MangaDiscussionAnswerService;
import pl.polsl.opinion_backend.services.works.manga.MangaDiscussionService;
import pl.polsl.opinion_backend.services.works.movie.MovieDiscussionAnswerService;
import pl.polsl.opinion_backend.services.works.movie.MovieDiscussionService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesDiscussionAnswerService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesDiscussionService;

import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class WorkOfCultureDiscussionManagingService {
    private final AnimeDiscussionService animeDiscussionService;
    private final MangaDiscussionService mangaDiscussionService;
    private final GameDiscussionService gameDiscussionService;
    private final TvSeriesDiscussionService tvSeriesDiscussionService;
    private final MovieDiscussionService movieDiscussionService;

    private final AnimeDiscussionAnswerService animeDiscussionAnswerService;
    private final MangaDiscussionAnswerService mangaDiscussionAnswerService;
    private final MovieDiscussionAnswerService movieDiscussionAnswerService;
    private final GameDiscussionAnswerService gameDiscussionAnswerService;
    private final TvSeriesDiscussionAnswerService tvSeriesDiscussionAnswerService;


    public void addDiscussion(WorkOfCultureType workOfCultureType, UUID workOfCultureId, DiscussionCreateDTO discussionCreateDTO) {

        switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO);
            case MANGA -> mangaDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO);
            case MOVIE -> movieDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO);
            case TVSERIES -> tvSeriesDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO);
            case GAME -> gameDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO);
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }

    }

    public void addAnswer(WorkOfCultureType workOfCultureType, UUID discussionId, AnswerCreateDTO answerCreateDTO) {

        switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.addAnswer(discussionId, answerCreateDTO);
            case MANGA -> mangaDiscussionService.addAnswer(discussionId, answerCreateDTO);
            case MOVIE -> movieDiscussionService.addAnswer(discussionId, answerCreateDTO);
            case TVSERIES -> tvSeriesDiscussionService.addAnswer(discussionId, answerCreateDTO);
            case GAME -> gameDiscussionService.addAnswer(discussionId, answerCreateDTO);
            default -> throw new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND);
        }

    }

    @Transactional
    public void deleteAllDiscussionsAndAnswersByCreateBy(UUID createBy) {
        animeDiscussionService.deleteAllByCreateBy(createBy);
        mangaDiscussionService.deleteAllByCreateBy(createBy);
        movieDiscussionService.deleteAllByCreateBy(createBy);
        tvSeriesDiscussionService.deleteAllByCreateBy(createBy);
        gameDiscussionService.deleteAllByCreateBy(createBy);

        animeDiscussionAnswerService.deleteAllByCreateBy(createBy);
        mangaDiscussionAnswerService.deleteAllByCreateBy(createBy);
        movieDiscussionAnswerService.deleteAllByCreateBy(createBy);
        tvSeriesDiscussionAnswerService.deleteAllByCreateBy(createBy);
        gameDiscussionAnswerService.deleteAllByCreateBy(createBy);

    }

}
