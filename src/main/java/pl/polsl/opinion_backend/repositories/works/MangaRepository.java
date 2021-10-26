package pl.polsl.opinion_backend.repositories.works;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;

import java.util.UUID;

@Repository
public interface MangaRepository extends WorkOfCultureRepository<Manga, UUID> {
}
