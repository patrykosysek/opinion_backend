package pl.polsl.opinion_backend.services.works.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.mappers.qualifires.GameGenresMapping;
import pl.polsl.opinion_backend.repositories.works.genre.GameGenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GENRE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameGenreService extends BasicService<GameGenre, GameGenreRepository> {

    @Override
    public GameGenre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GENRE_NOT_FOUND));
    }

    public GameGenre getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NoSuchElementException(GENRE_NOT_FOUND));
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }


    public Set<GenreType> getAllGenres() {
        Iterable<GameGenre> animeMangaGenres = findAll();
        Set<GenreType> genreTypes = new HashSet<>();
        animeMangaGenres.forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    genreTypes.add(genreType);
            }
        });
        return genreTypes;
    }

    @GameGenresMapping
    public Set<GenreType> getGenreNames(Set<GameGenre> genres) {
        Set<GenreType> genreTypes = new HashSet<>();
        genres.forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    genreTypes.add(genreType);
            }
        });
        return genreTypes;
    }

}