package pl.polsl.opinion_backend.services.works.discussion;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.*;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.discussion.*;
import pl.polsl.opinion_backend.mappers.workOfCultureMapper.discussion.answer.*;
import pl.polsl.opinion_backend.services.user.UserService;
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

@RequiredArgsConstructor
@Service
public class DiscussionManagingService {
    private final AnimeDiscussionService animeDiscussionService;
    private final MangaDiscussionService mangaDiscussionService;
    private final MovieDiscussionService movieDiscussionService;
    private final GameDiscussionService gameDiscussionService;
    private final TvSeriesDiscussionService tvSeriesDiscussionService;

    private final UserService userService;

    private final AnimeDiscussionMapper animeDiscussionMapper;
    private final MangaDiscussionMapper mangaDiscussionMapper;
    private final MovieDiscussionMapper movieDiscussionMapper;
    private final TvSeriesDiscussionMapper tvSeriesDiscussionMapper;
    private final GameDiscussionMapper gameDiscussionMapper;

    private final AnimeDiscussionAnswerService animeDiscussionAnswerService;
    private final MangaDiscussionAnswerService mangaDiscussionAnswerService;
    private final MovieDiscussionAnswerService movieDiscussionAnswerService;
    private final TvSeriesDiscussionAnswerService tvSeriesDiscussionAnswerService;
    private final GameDiscussionAnswerService gameDiscussionAnswerService;

    private final AnimeAnswerDiscussionMapper animeAnswerDiscussionMapper;
    private final MangaAnswerDiscussionMapper mangaAnswerDiscussionMapper;
    private final MovieAnswerDiscussionMapper movieAnswerDiscussionMapper;
    private final TvSeriesAnswerDiscussionMapper tvSeriesAnswerDiscussionMapper;
    private final GameAnswerDiscussionMapper gameAnswerDiscussionMapper;


    public Page<DiscussionResponseDTO> getDiscussionByCreateBy(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateBy(userId, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateBy(userId, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateBy(userId, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateBy(userId, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateBy(userId, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopic(WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCase(userId, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCase(userId, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCase(userId, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCase(userId, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCase(userId, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopicDesc(WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(userId, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(userId, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(userId, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(userId, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(userId, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopicAsc(WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(userId, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(userId, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(userId, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(userId, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(userId, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByCreateByDesc(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateByOrderByCreateDateDesc(userId, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateByOrderByCreateDateDesc(userId, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateByOrderByCreateDateDesc(userId, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateByOrderByCreateDateDesc(userId, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateByOrderByCreateDateDesc(userId, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByCreateByAsc(WorkOfCultureType workOfCultureType, Pageable pageable) {
        UUID userId = userService.getCurrentUser().getId();

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByCreateByOrderByCreateDateAsc(userId, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByCreateByOrderByCreateDateAsc(userId, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByCreateByOrderByCreateDateAsc(userId, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByCreateByOrderByCreateDateAsc(userId, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByCreateByOrderByCreateDateAsc(userId, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }


    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureId(UUID id, WorkOfCultureType workOfCultureType, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeId(id, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaId(id, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieId(id, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesId(id, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameId(id, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopic(UUID id, WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeIdAndTopicStartingWithIgnoreCase(id, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaIdAndTopicStartingWithIgnoreCase(id, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieIdAndTopicStartingWithIgnoreCase(id, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesIdAndTopicStartingWithIgnoreCase(id, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameIdAndTopicStartingWithIgnoreCase(id, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopicDesc(UUID id, WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopicAsc(UUID id, WorkOfCultureType workOfCultureType, String topic, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdDesc(UUID id, WorkOfCultureType workOfCultureType, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeIdOrderByCreateDateDesc(id, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaIdOrderByCreateDateDesc(id, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieIdOrderByCreateDateDesc(id, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesIdOrderByCreateDateDesc(id, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameIdOrderByCreateDateDesc(id, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAsc(UUID id, WorkOfCultureType workOfCultureType, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionService.findAllByAnimeIdOrderByCreateDateAsc(id, pageable).map(animeDiscussionMapper::toDiscussionResponseDTO);
            case MANGA -> mangaDiscussionService.findAllByMangaIdOrderByCreateDateAsc(id, pageable).map(mangaDiscussionMapper::toDiscussionResponseDTO);
            case MOVIE -> movieDiscussionService.findAllByMovieIdOrderByCreateDateAsc(id, pageable).map(movieDiscussionMapper::toDiscussionResponseDTO);
            case TVSERIES -> tvSeriesDiscussionService.findAllByTvSeriesIdOrderByCreateDateAsc(id, pageable).map(tvSeriesDiscussionMapper::toDiscussionResponseDTO);
            case GAME -> gameDiscussionService.findAllByGameIdOrderByCreateDateAsc(id, pageable).map(gameDiscussionMapper::toDiscussionResponseDTO);
        };

    }

    public DiscussionInformationResponseDTO getDiscussionInformation(UUID id, WorkOfCultureType workOfCultureType, Pageable pageable) {

        return switch (workOfCultureType) {
            case ANIME -> getAnimeDiscussionInformation(id, pageable);
            case MANGA -> getMangaDiscussionInformation(id, pageable);
            case MOVIE -> getMovieDiscussionInformation(id, pageable);
            case TVSERIES -> getTvSeriesDiscussionInformation(id, pageable);
            case GAME -> getGameDiscussionInformation(id, pageable);
        };
    }

    public DiscussionInformationResponseDTO getAnimeDiscussionInformation(UUID id, Pageable pageable) {
        AnimeDiscussion animeDiscussion = animeDiscussionService.getById(id);
        Page<AnswerResponseDTO> answers = animeDiscussionAnswerService.findAllByDiscussion(animeDiscussion, pageable).map(animeAnswerDiscussionMapper::toAnswerResponseDTO);

        return animeDiscussionMapper.toDiscussionInformationResponseDTO(animeDiscussion, answers);
    }

    public DiscussionInformationResponseDTO getMangaDiscussionInformation(UUID id, Pageable pageable) {
        MangaDiscussion mangaDiscussion = mangaDiscussionService.getById(id);
        Page<AnswerResponseDTO> answers = mangaDiscussionAnswerService.findAllByDiscussion(mangaDiscussion, pageable).map(mangaAnswerDiscussionMapper::toAnswerResponseDTO);

        return mangaDiscussionMapper.toDiscussionInformationResponseDTO(mangaDiscussion, answers);
    }

    public DiscussionInformationResponseDTO getMovieDiscussionInformation(UUID id, Pageable pageable) {
        MovieDiscussion movieDiscussion = movieDiscussionService.getById(id);
        Page<AnswerResponseDTO> answers = movieDiscussionAnswerService.findAllByDiscussion(movieDiscussion, pageable).map(movieAnswerDiscussionMapper::toAnswerResponseDTO);

        return movieDiscussionMapper.toDiscussionInformationResponseDTO(movieDiscussion, answers);
    }

    public DiscussionInformationResponseDTO getTvSeriesDiscussionInformation(UUID id, Pageable pageable) {
        TvSeriesDiscussion tvSeriesDiscussion = tvSeriesDiscussionService.getById(id);
        Page<AnswerResponseDTO> answers = tvSeriesDiscussionAnswerService.findAllByDiscussion(tvSeriesDiscussion, pageable).map(tvSeriesAnswerDiscussionMapper::toAnswerResponseDTO);

        return tvSeriesDiscussionMapper.toDiscussionInformationResponseDTO(tvSeriesDiscussion, answers);
    }

    public DiscussionInformationResponseDTO getGameDiscussionInformation(UUID id, Pageable pageable) {
        GameDiscussion gameDiscussion = gameDiscussionService.getById(id);
        Page<AnswerResponseDTO> answers = gameDiscussionAnswerService.findAllByDiscussion(gameDiscussion, pageable).map(gameAnswerDiscussionMapper::toAnswerResponseDTO);

        return gameDiscussionMapper.toDiscussionInformationResponseDTO(gameDiscussion, answers);
    }

    public DiscussionResponseDTO addDiscussion(WorkOfCultureType workOfCultureType, UUID workOfCultureId, DiscussionCreateDTO discussionCreateDTO) {

        return switch (workOfCultureType) {
            case ANIME -> animeDiscussionMapper.toDiscussionResponseDTO(animeDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO));
            case MANGA -> mangaDiscussionMapper.toDiscussionResponseDTO(mangaDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO));
            case MOVIE -> movieDiscussionMapper.toDiscussionResponseDTO(movieDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO));
            case TVSERIES -> tvSeriesDiscussionMapper.toDiscussionResponseDTO(tvSeriesDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO));
            case GAME -> gameDiscussionMapper.toDiscussionResponseDTO(gameDiscussionService.addDiscussion(workOfCultureId, discussionCreateDTO));
        };
    }

    public AnswerResponseDTO addAnswer(WorkOfCultureType workOfCultureType, UUID discussionId, AnswerCreateDTO answerCreateDTO) {

        return switch (workOfCultureType) {
            case ANIME -> animeAnswerDiscussionMapper.toAnswerResponseDTO(animeDiscussionService.addAnswer(discussionId, answerCreateDTO));
            case MANGA -> mangaAnswerDiscussionMapper.toAnswerResponseDTO(mangaDiscussionService.addAnswer(discussionId, answerCreateDTO));
            case MOVIE -> movieAnswerDiscussionMapper.toAnswerResponseDTO(movieDiscussionService.addAnswer(discussionId, answerCreateDTO));
            case TVSERIES -> tvSeriesAnswerDiscussionMapper.toAnswerResponseDTO(tvSeriesDiscussionService.addAnswer(discussionId, answerCreateDTO));
            case GAME -> gameAnswerDiscussionMapper.toAnswerResponseDTO(gameDiscussionService.addAnswer(discussionId, answerCreateDTO));
        };
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
