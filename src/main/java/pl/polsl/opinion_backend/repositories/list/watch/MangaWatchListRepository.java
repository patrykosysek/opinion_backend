package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaWatchListRepository extends BasicRepository<MangaWatchList, UUID> {

    boolean existsByWatchListAndManga(WatchList watchList, Manga manga);

    boolean existsByWatchListAndManga_Id(WatchList watchList, UUID mangaId);

    Optional<MangaWatchList> findByManga_IdAndWatchList(UUID mangaId, WatchList watchList);

    Set<MangaWatchList> findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaWatchList> findAllByManga_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<MangaWatchList> findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<MangaWatchList> findAllByMangaGenresNameAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date);

    Set<MangaWatchList> findAllByMangaGenresName(String name);


}
