package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.works.MangaRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaService extends WorkOfCultureService<Manga, MangaRepository> {

    @Override
    public Manga getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

}
