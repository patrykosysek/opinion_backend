package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesDiscussionAnswerRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_DISCUSSION_ANSWER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesDiscussionAnswerService extends BasicService<TvSeriesDiscussionAnswer, TvSeriesDiscussionAnswerRepository> {

    @Override
    public TvSeriesDiscussionAnswer getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_DISCUSSION_ANSWER_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

}
