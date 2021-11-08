package pl.polsl.opinion_backend.repositories.list.seen;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AnimeSeenListRepository extends BasicRepository<AnimeSeenList, UUID> {

    boolean existsBySeenListAndAnime(SeenList seenList, Anime anime);

    Optional<AnimeSeenList> findByAnime_IdAndSeenList(UUID animeId, SeenList seenList);

    Set<AnimeSeenList> findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeSeenList> findAllByAnime_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<AnimeSeenList> findAllByAnimeGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeSeenList> findAllByAnimeGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<AnimeSeenList> findAllByAnimeGenresName(String name);

}
