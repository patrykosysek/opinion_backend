package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.entities.base.Genre;
import pl.polsl.opinion_backend.repositories.works.GenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GenreService extends BasicService<Genre, GenreRepository> {

    @Override
    @Transactional(readOnly = true)
    public Genre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
