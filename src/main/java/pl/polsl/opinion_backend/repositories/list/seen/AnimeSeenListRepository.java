package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimeSeenListRepository extends BasicRepository<AnimeSeenList, UUID> {

    boolean existsBySeenListAndAnime(SeenList seenList, Anime anime);

    Optional<AnimeSeenList> findByAnime_IdAndSeenList(UUID animeId, SeenList seenList);

}
