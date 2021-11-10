package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesDiscussionService extends BasicService<TvSeriesDiscussion, TvSeriesDiscussionRepository> {
    private final TvSeriesService tvSeriesService;
    private final TvSeriesDiscussionAnswerService tvSeriesDiscussionAnswerService;

    @Override
    public TvSeriesDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_DISCUSSION_NOT_FOUND));
    }

    public TvSeriesDiscussion addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);
        TvSeriesDiscussion tvSeriesDiscussion = new TvSeriesDiscussion();
        tvSeriesDiscussion.addTvSeries(tvSeries);
        tvSeriesDiscussion.setText(dto.getText());
        tvSeriesDiscussion.setTopic(dto.getTopic());
        tvSeries.getStatistic().setCurrentDiscussion(tvSeries.getStatistic().getCurrentDiscussion() + 1);
        tvSeriesService.save(tvSeries);
        return save(tvSeriesDiscussion);
    }

    public TvSeriesDiscussionAnswer addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        TvSeriesDiscussion tvSeriesDiscussion = getById(discussionId);
        TvSeriesDiscussionAnswer tvSeriesDiscussionAnswer = new TvSeriesDiscussionAnswer();
        tvSeriesDiscussionAnswer.setText(dto.getText());
        tvSeriesDiscussion.addAnswer(tvSeriesDiscussionAnswer);
        TvSeries tvSeries = tvSeriesDiscussion.getTvSeries();
        tvSeries.getStatistic().setCurrentDiscussion(tvSeries.getStatistic().getCurrentDiscussion() + 1);
        tvSeriesService.save(tvSeries);
        return tvSeriesDiscussionAnswerService.save(tvSeriesDiscussionAnswer);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<TvSeriesDiscussion> findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<TvSeriesDiscussion> findAllByTvSeriesIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByTvSeries_IdAndCreateDateIsBefore(id, date);
    }

    public Set<TvSeriesDiscussion> findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeriesGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<TvSeriesDiscussion> findAllByTvSeriesGenresAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByTvSeriesGenresNameAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

    public Set<TvSeriesDiscussion> findAllByGenresName(String genre) {
        return repository.findAllByTvSeriesGenresName(genre);
    }

    public Page<TvSeriesDiscussion> findAllByCreateBy(UUID id, Pageable pageable) {
        return repository.findAllByCreateBy(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateAsc(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateDesc(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesId(UUID id, Pageable pageable) {
        return repository.findAllByTvSeries_Id(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesIdOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByTvSeries_IdOrderByCreateDateAsc(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesIdOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByTvSeries_IdOrderByCreateDateDesc(id, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesIdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByTvSeries_IdAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByTvSeries_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<TvSeriesDiscussion> findAllByTvSeriesIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByTvSeries_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

}
