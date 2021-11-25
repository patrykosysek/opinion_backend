package pl.polsl.opinion_backend.repositories.works.tvSeries;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TvSeriesDiscussionAnswerRepository extends BasicRepository<TvSeriesDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    List<TvSeriesDiscussionAnswer> findAllByDiscussion(TvSeriesDiscussion tvSeriesDiscussion);

}
