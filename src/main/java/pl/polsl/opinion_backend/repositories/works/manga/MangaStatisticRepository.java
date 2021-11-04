package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaStatistic;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MangaStatisticRepository extends BasicRepository<MangaStatistic, UUID> {
}
