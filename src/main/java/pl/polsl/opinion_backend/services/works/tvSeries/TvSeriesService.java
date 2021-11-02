package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesService extends WorkOfCultureService<TvSeries, TvSeriesRepository> {

    @Override
    public TvSeries getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_NOT_FOUND));
    }

}
