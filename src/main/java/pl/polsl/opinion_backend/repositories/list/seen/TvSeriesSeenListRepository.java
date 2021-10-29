package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TvSeriesSeenListRepository extends BasicRepository<TvSeriesSeenList, UUID> {

    boolean existsBySeenListAndTvSeries(SeenList seenList, TvSeries tvSeries);

    Optional<TvSeriesSeenList> findByTvSeries_IdAndSeenList(UUID tvSeriesId, SeenList seenList);

}
