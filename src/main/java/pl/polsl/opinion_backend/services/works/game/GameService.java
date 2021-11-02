package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.repositories.works.game.GameRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameService extends WorkOfCultureService<Game, GameRepository> {

    @Override
    public Game getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_NOT_FOUND));
    }

}
