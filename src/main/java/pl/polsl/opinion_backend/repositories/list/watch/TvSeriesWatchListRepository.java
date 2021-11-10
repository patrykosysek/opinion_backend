package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TvSeriesWatchListRepository extends BasicRepository<TvSeriesWatchList, UUID> {

    boolean existsByWatchListAndTvSeries(WatchList watchList, TvSeries tvSeries);

    boolean existsByWatchListAndTvSeries_Id(WatchList watchList, UUID tvSeriesId);

    Optional<TvSeriesWatchList> findByTvSeries_IdAndWatchList(UUID tvSeriesId, WatchList watchList);

    Set<TvSeriesWatchList> findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesWatchList> findAllByTvSeries_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<TvSeriesWatchList> findAllByTvSeriesGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesWatchList> findAllByTvSeriesGenresNameAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date);

    Set<TvSeriesWatchList> findAllByTvSeriesGenresName(String name);

    Page<TvSeriesWatchList> findAllByWatchList(WatchList watchList, Pageable pageable);

}
