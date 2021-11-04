package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesDiscussionService extends BasicService<TvSeriesDiscussion, TvSeriesDiscussionRepository> {
    private final TvSeriesService tvSeriesService;

    @Override
    public TvSeriesDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_DISCUSSION_NOT_FOUND));
    }

    public void addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        TvSeries tvSeries = tvSeriesService.getById(workOfCultureId);
        TvSeriesDiscussion tvSeriesDiscussion = new TvSeriesDiscussion();
        tvSeriesDiscussion.addTvSeries(tvSeries);
        tvSeriesDiscussion.setText(dto.getText());
        tvSeriesDiscussion.setTopic(dto.getTopic());
        tvSeries.getStatistic().setCurrentDiscussion(tvSeries.getStatistic().getCurrentDiscussion() + 1);
        tvSeriesService.save(tvSeries);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        TvSeriesDiscussion tvSeriesDiscussion = getById(discussionId);
        TvSeriesDiscussionAnswer tvSeriesDiscussionAnswer = new TvSeriesDiscussionAnswer();
        tvSeriesDiscussionAnswer.setText(dto.getText());
        tvSeriesDiscussion.addAnswer(tvSeriesDiscussionAnswer);
        TvSeries tvSeries = tvSeriesDiscussion.getTvSeries();
        tvSeries.getStatistic().setCurrentDiscussion(tvSeries.getStatistic().getCurrentDiscussion() + 1);
        tvSeriesService.save(tvSeries);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

}
