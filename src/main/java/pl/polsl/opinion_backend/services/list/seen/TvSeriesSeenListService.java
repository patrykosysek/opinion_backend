package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.list.seen.TvSeriesSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
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

    public Set<TvSeriesSeenList> findAllByTvSeriesIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<TvSeriesSeenList> findAllByTvSeriesIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByTvSeries_IdAndCreateDateIsBefore(id, date);
    }

    public Set<TvSeriesSeenList> findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByTvSeriesGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(movieTvSeriesGenre, startDate, endDate);
    }

    public Set<TvSeriesSeenList> findAllByTvSeriesGenresAndCreateDateIsBefore(String movieTvSeriesGenre, OffsetDateTime date) {
        return repository.findAllByTvSeriesGenresNameAndCreateDateIsBefore(movieTvSeriesGenre, date);
    }

    public Set<TvSeriesSeenList> findAllByGenresName(String genre) {
        return repository.findAllByTvSeriesGenresName(genre);
    }

    public Page<TvSeriesSeenList> getAllBySeenList(SeenList seenList, Pageable pageable) {
        return repository.findAllBySeenList(seenList, pageable);
    }

}
