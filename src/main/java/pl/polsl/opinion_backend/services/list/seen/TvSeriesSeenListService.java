package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.list.seen.TvSeriesSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_SEEN_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesSeenListService extends BasicService<TvSeriesSeenList, TvSeriesSeenListRepository> {

    @Override
    public TvSeriesSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_SEEN_LIST_NOT_FOUND));
    }

    public boolean existsBySeenListAndTvSeries(SeenList seenList, TvSeries tvSeries) {
        return repository.existsBySeenListAndTvSeries(seenList, tvSeries);
    }

    public TvSeriesSeenList findByTvSeriesIdAndSeenList(UUID tvSeriesId, SeenList seenList) {
        return repository.findByTvSeries_IdAndSeenList(tvSeriesId, seenList).orElseThrow(() -> new IllegalArgumentException(TV_SERIES_SEEN_LIST_NOT_FOUND));
    }

}
