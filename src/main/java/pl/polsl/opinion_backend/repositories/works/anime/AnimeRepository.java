package pl.polsl.opinion_backend.repositories.works.anime;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.works.WorkOfCultureRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimeRepository extends WorkOfCultureRepository<Anime, UUID> {

    boolean existsByTitle(String title);

    boolean existsByApiId(String id);

    Optional<Anime> findByApiId(String id);

    Set<Anime> findAllByGenres(AnimeMangaGenre genre);

}
