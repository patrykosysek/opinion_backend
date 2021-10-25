package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.TvSeries;
import pl.polsl.opinion_backend.repositories.works.TvSeriesRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesService extends BasicService<TvSeries, TvSeriesRepository> {

    @Override
    public TvSeries getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    public boolean existsByApiId(String id) {
        return repository.existsByApiId(id);
    }

    public TvSeries getByApiId(String apiId) {
        return repository.findByApiId(apiId).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
