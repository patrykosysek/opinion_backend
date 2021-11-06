package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeService extends WorkOfCultureService<Anime, AnimeRepository> {

    @Override
    public Anime getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_NOT_FOUND));
    }

    public Set<Anime> getAllByGenres(AnimeMangaGenre genre) {
        return repository.findAllByGenres(genre);
    }

}
