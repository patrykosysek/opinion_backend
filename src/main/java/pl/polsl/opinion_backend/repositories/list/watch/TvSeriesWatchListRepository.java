package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TvSeriesWatchListRepository extends BasicRepository<TvSeriesWatchList, UUID> {

    boolean existsByWatchListAndTvSeries(WatchList watchList, TvSeries tvSeries);

    boolean existsByWatchListAndTvSeries_Id(WatchList watchList, UUID tvSeriesId);

    Optional<TvSeriesWatchList> findByTvSeries_IdAndWatchList(UUID tvSeriesId, WatchList watchList);

}
