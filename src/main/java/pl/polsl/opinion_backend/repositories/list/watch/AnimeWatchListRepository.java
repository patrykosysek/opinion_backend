package pl.polsl.opinion_backend.repositories.list.watch;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimeWatchListRepository extends BasicRepository<AnimeWatchList, UUID> {

    boolean existsByWatchListAndAnime(WatchList watchList, Anime anime);

    boolean existsByWatchListAndAnime_Id(WatchList watchList, UUID animeId);

    Optional<AnimeWatchList> findByAnime_IdAndWatchList(UUID animeId, WatchList watchList);

}
