package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.StatisticResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureStatisticResponseDTO;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeService extends WorkOfCultureService<Anime, AnimeRepository> {

    @Override
    public Anime getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_NOT_FOUND));
    }

    public Set<Anime> getAllByGenres(AnimeMangaGenre genre) {
        return repository.findAllByGenres(genre);
    }

    public WorkOfCultureStatisticResponseDTO getStatistic(UUID id) {
        Anime anime = getById(id);
        WorkOfCultureStatisticResponseDTO workOfCultureStatisticResponseDTO = new WorkOfCultureStatisticResponseDTO();
        workOfCultureStatisticResponseDTO.setStatisticResponseDTO(new StatisticResponseDTO());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setDiscussionCount(anime.getDiscussions().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setReviewCount(anime.getReviews().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setSeenListCount(anime.getAnimeSeenLists().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setWatchListCount(anime.getAnimeWatchLists().size());

        workOfCultureStatisticResponseDTO.setId(id);
        workOfCultureStatisticResponseDTO.setImageUrl(anime.getImageUrl());
        workOfCultureStatisticResponseDTO.setWorkOfCultureType(WorkOfCultureType.ANIME);
        workOfCultureStatisticResponseDTO.setTitle(anime.getTitle());

        anime.getGenres().forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    workOfCultureStatisticResponseDTO.getGenres().add(genreType);
            }
        });
        return workOfCultureStatisticResponseDTO;
    }

}
