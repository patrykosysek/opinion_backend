package pl.polsl.opinion_backend.services.works.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureStatisticResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.works.manga.MangaRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaService extends WorkOfCultureService<Manga, MangaRepository> {

    @Override
    public Manga getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_NOT_FOUND));
    }

    public WorkOfCultureStatisticResponseDTO getStatistic(UUID id) {
        Manga manga = getById(id);
        WorkOfCultureStatisticResponseDTO workOfCultureStatisticResponseDTO = new WorkOfCultureStatisticResponseDTO();
        workOfCultureStatisticResponseDTO.setDiscussionCount(manga.getDiscussions().size());
        workOfCultureStatisticResponseDTO.setReviewCount(manga.getReviews().size());
        workOfCultureStatisticResponseDTO.setSeenListCount(manga.getMangaSeenLists().size());
        workOfCultureStatisticResponseDTO.setWatchListCount(manga.getMangaWatchLists().size());

        workOfCultureStatisticResponseDTO.setId(id);
        workOfCultureStatisticResponseDTO.setImageUrl(manga.getImageUrl());
        workOfCultureStatisticResponseDTO.setWorkOfCultureType(WorkOfCultureType.MANGA);
        workOfCultureStatisticResponseDTO.setTitle(manga.getTitle());

        manga.getGenres().forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    workOfCultureStatisticResponseDTO.getGenres().add(genreType);
            }
        });
        return workOfCultureStatisticResponseDTO;
    }

}
