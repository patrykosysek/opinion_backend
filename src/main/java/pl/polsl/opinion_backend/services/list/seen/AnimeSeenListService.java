package pl.polsl.opinion_backend.services.list.seen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.repositories.list.seen.AnimeSeenListRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_SEEN_LIST_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class AnimeSeenListService extends BasicService<AnimeSeenList, AnimeSeenListRepository> {

    @Override
    public AnimeSeenList getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_SEEN_LIST_NOT_FOUND));
    }

    public boolean existsBySeenListAndAnime(SeenList seenList, Anime anime) {
        return repository.existsBySeenListAndAnime(seenList, anime);
    }

    public AnimeSeenList findByAnimeIdAndSeenList(UUID animeId, SeenList seenList) {
        return repository.findByAnime_IdAndSeenList(animeId, seenList).orElseThrow(() -> new IllegalArgumentException(ANIME_SEEN_LIST_NOT_FOUND));
    }

    public Set<AnimeSeenList> findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<AnimeSeenList> findAllByAnimeIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByAnime_IdAndCreateDateIsBefore(id, date);
    }

    public Set<AnimeSeenList> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<AnimeSeenList> findAllByAnimeGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<AnimeSeenList> findAllByGenresName(String genre) {
        return repository.findAllByAnimeGenresName(genre);
    }

}
