package pl.polsl.opinion_backend.repositories.works.anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimeDiscussionRepository extends BasicRepository<AnimeDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<AnimeDiscussion> findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeDiscussion> findAllByAnime_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<AnimeDiscussion> findAllByAnimeGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeDiscussion> findAllByAnimeGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<AnimeDiscussion> findAllByAnimeGenresName(String name);

    Page<AnimeDiscussion> findAllByCreateBy(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);


    Page<AnimeDiscussion> findAllByAnime_Id(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByAnime_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByAnime_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<AnimeDiscussion> findAllByAnime_IdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<AnimeDiscussion> findAllByAnime_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<AnimeDiscussion> findAllByAnime_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

}

