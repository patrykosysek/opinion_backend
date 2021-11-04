package pl.polsl.opinion_backend.services.works.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaStatistic;
import pl.polsl.opinion_backend.repositories.works.manga.MangaStatisticRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_STATISTIC_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaStatisticService extends BasicService<MangaStatistic, MangaStatisticRepository> {

    @Override
    public MangaStatistic getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_STATISTIC_NOT_FOUND));
    }

}
