package pl.polsl.opinion_backend.services.list.watch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.list.watch.TvSeriesWatchListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesWatchListService extends BasicService<TvSeriesWatchList, TvSeriesWatchListRepository> {

    @Override
    public TvSeriesWatchList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

    public boolean existsByWatchListAndTvSeries(WatchList watchList, TvSeries tvSeries) {
        return repository.existsByWatchListAndTvSeries(watchList, tvSeries);
    }


    public boolean existsByWatchListAndTvSeriesId(WatchList watchList, UUID tvSeriesId) {
        return repository.existsByWatchListAndTvSeries_Id(watchList, tvSeriesId);
    }

    public TvSeriesWatchList findByTvSeriesIdAndWatchList(UUID tvSeriesId, WatchList watchList) {
        return repository.findByTvSeries_IdAndWatchList(tvSeriesId, watchList).orElseThrow(() -> new IllegalArgumentException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
