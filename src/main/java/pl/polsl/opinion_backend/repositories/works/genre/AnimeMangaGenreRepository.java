package pl.polsl.opinion_backend.repositories.works.genre;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface AnimeMangaGenreRepository extends BasicRepository<AnimeMangaGenre, UUID> {
}
