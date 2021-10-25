package pl.polsl.opinion_backend.services.works.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.repositories.works.genre.GameGenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameGenreService extends BasicService<GameGenre, GameGenreRepository> {

    @Override
    public GameGenre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    public GameGenre getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
