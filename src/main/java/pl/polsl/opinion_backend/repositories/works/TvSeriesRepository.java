package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;

import java.util.UUID;

@Repository
public interface TvSeriesRepository extends WorkOfCultureRepository<TvSeries, UUID> {
}
