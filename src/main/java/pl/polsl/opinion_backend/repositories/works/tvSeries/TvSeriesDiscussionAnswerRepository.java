package pl.polsl.opinion_backend.repositories.works.tvSeries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface TvSeriesDiscussionAnswerRepository extends BasicRepository<TvSeriesDiscussionAnswer, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Page<TvSeriesDiscussionAnswer> findAllByDiscussion(TvSeriesDiscussion tvSeriesDiscussion, Pageable pageable);

}
