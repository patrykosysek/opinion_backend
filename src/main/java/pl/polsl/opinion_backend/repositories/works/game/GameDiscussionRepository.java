package pl.polsl.opinion_backend.repositories.works.game;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
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

    Set<GameDiscussion> findAllByGameGenresAndCreateDateIsAfterAndCreateDateIsBefore(GameGenre gameGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<GameDiscussion> findAllByGameGenresAndCreateDateIsBefore(GameGenre gameGenre, OffsetDateTime date);

}
