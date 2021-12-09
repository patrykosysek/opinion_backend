package pl.polsl.opinion_backend.repositories.works.tvSeries;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesStatistic;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface TvSeriesStatisticRepository extends BasicRepository<TvSeriesStatistic, UUID> {
}
