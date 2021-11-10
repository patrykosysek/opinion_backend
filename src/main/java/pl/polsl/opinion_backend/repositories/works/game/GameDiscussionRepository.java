package pl.polsl.opinion_backend.repositories.works.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameDiscussionRepository extends BasicRepository<GameDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<GameDiscussion> findAllByGame_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameDiscussion> findAllByGame_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<GameDiscussion> findAllByGameGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String gameGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameDiscussion> findAllByGameGenresNameAndCreateDateIsBefore(String gameGenre, OffsetDateTime date);

    Set<GameDiscussion> findAllByGameGenresName(String name);

    Page<GameDiscussion> findAllByCreateBy(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<GameDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

    Page<GameDiscussion> findAllByGame_Id(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByGame_IdOrderByCreateDateAsc(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByGame_IdOrderByCreateDateDesc(UUID id, Pageable pageable);

    Page<GameDiscussion> findAllByGame_IdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable);

    Page<GameDiscussion> findAllByGame_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable);

    Page<GameDiscussion> findAllByGame_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable);

}
