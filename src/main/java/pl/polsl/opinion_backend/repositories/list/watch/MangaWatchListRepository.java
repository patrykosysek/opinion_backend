package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaWatchListRepository extends BasicRepository<MangaWatchList, UUID> {

    boolean existsByWatchListAndManga(WatchList watchList, Manga manga);

    boolean existsByWatchListAndManga_Id(WatchList watchList, UUID mangaId);

    Optional<MangaWatchList> findByManga_IdAndWatchList(UUID mangaId, WatchList watchList);

}
