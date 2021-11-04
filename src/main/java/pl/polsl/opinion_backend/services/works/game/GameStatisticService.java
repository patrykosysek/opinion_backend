package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameStatistic;
import pl.polsl.opinion_backend.repositories.works.game.GameStatisticRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_STATISTIC_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameStatisticService extends BasicService<GameStatistic, GameStatisticRepository> {

    @Override
    public GameStatistic getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_STATISTIC_NOT_FOUND));
    }

}
