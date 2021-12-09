package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeStatistic;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeStatisticRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_STATISTIC_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeStatisticService extends BasicService<AnimeStatistic, AnimeStatisticRepository> {

    @Override
    public AnimeStatistic getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_STATISTIC_NOT_FOUND));
    }
    
}
