package pl.polsl.opinion_backend.services.works.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeMangaGenresMapping;
import pl.polsl.opinion_backend.repositories.works.genre.AnimeMangaGenreRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GENRE_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class AnimeMangaGenreService extends BasicService<AnimeMangaGenre, AnimeMangaGenreRepository> {

    @Override
    public AnimeMangaGenre getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GENRE_NOT_FOUND));
    }

    public AnimeMangaGenre getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new IllegalArgumentException(GENRE_NOT_FOUND));
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public Set<GenreType> getAllGenres() {
        Iterable<AnimeMangaGenre> animeMangaGenres = findAll();
        Set<GenreType> genreTypes = new HashSet<>();
        animeMangaGenres.forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    genreTypes.add(genreType);
            }
        });
        return genreTypes;
    }

    @AnimeMangaGenresMapping
    public Set<GenreType> getGenreNames(Set<AnimeMangaGenre> genres) {
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
