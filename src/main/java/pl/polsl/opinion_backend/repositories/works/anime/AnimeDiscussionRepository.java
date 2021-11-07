package pl.polsl.opinion_backend.repositories.works.anime;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
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

    Set<AnimeDiscussion> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<AnimeDiscussion> findAllByAnimeGenresAndCreateDateIsBefore(AnimeMangaGenre animeMangaGenre, OffsetDateTime date);

}
