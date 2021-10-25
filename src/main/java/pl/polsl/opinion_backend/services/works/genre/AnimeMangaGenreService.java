package pl.polsl.opinion_backend.services.works.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.repositories.works.genre.AnimeMangaGenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class AnimeMangaGenreService extends BasicService<AnimeMangaGenre, AnimeMangaGenreRepository> {

    @Override
    public AnimeMangaGenre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
