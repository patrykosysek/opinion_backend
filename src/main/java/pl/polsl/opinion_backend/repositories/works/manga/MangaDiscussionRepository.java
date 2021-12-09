package pl.polsl.opinion_backend.repositories.works.manga;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
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

    Set<MangaDiscussion> findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaDiscussion> findAllByMangaGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<MangaDiscussion> findAllByMangaGenresName(String name);

    Page<MangaDiscussion> findAllByCreateBy(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_Id(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_IdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<MangaDiscussion> findAllByManga_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

}
