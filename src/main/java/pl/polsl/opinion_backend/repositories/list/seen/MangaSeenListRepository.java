package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaSeenListRepository extends BasicRepository<MangaSeenList, UUID> {

    boolean existsBySeenListAndManga(SeenList seenList, Manga manga);

    Optional<MangaSeenList> findByManga_IdAndSeenList(UUID mangaId, SeenList seenList);

    Set<MangaSeenList> findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaSeenList> findAllByManga_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MangaSeenList> findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaSeenList> findAllByMangaGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<MangaSeenList> findAllByMangaGenresName(String name);


}
