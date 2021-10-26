package pl.polsl.opinion_backend.services.works;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.works.AnimeRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.WORK_OF_CULTURE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeService extends WorkOfCultureService<Anime, AnimeRepository> {

    @Override
    public Anime getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(WORK_OF_CULTURE_NOT_FOUND));
    }

}
