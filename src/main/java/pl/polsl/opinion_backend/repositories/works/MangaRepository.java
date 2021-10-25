package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.Manga;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaRepository extends BasicRepository<Manga, UUID> {

    boolean existsByTitle(String title);

    boolean existsByApiId(String id);

    Optional<Manga> findByApiId(String id);

}
