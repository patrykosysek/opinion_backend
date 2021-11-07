package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaDiscussionRepository extends BasicRepository<MangaDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<MangaDiscussion> findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaDiscussion> findAllByManga_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MangaDiscussion> findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaDiscussion> findAllByMangaGenresAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime date);

}
