package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.repositories.works.TvSeriesRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesService extends WorkOfCultureService<TvSeries, TvSeriesRepository> {

    @Override
    public TvSeries getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
