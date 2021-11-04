package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesStatistic;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesStatisticRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_STATISTIC_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesStatisticService extends BasicService<TvSeriesStatistic, TvSeriesStatisticRepository> {

    @Override
    public TvSeriesStatistic getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_STATISTIC_NOT_FOUND));
    }

}
