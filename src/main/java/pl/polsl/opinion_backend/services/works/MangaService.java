package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.Manga;
import pl.polsl.opinion_backend.repositories.works.MangaRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaService extends BasicService<Manga, MangaRepository> {

    @Override
    public Manga getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    public boolean existsByApiId(String id) {
        return repository.existsByApiId(id);
    }

    public Manga getByApiId(String apiId) {
        return repository.findByApiId(apiId).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }
}
