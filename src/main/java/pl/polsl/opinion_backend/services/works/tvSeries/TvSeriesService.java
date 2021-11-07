package pl.polsl.opinion_backend.services.works.tvSeries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureStatisticResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.works.tvSeries.TvSeriesRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TV_SERIES_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TvSeriesService extends WorkOfCultureService<TvSeries, TvSeriesRepository> {

    @Override
    public TvSeries getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(TV_SERIES_NOT_FOUND));
    }

    public WorkOfCultureStatisticResponseDTO getStatistic(UUID id) {
        TvSeries tvSeries = getById(id);
        WorkOfCultureStatisticResponseDTO workOfCultureStatisticResponseDTO = new WorkOfCultureStatisticResponseDTO();
        workOfCultureStatisticResponseDTO.setDiscussionCount(tvSeries.getDiscussions().size());
        workOfCultureStatisticResponseDTO.setReviewCount(tvSeries.getReviews().size());
        workOfCultureStatisticResponseDTO.setSeenListCount(tvSeries.getTvSeriesSeenLists().size());
        workOfCultureStatisticResponseDTO.setWatchListCount(tvSeries.getTvSeriesWatchLists().size());

        workOfCultureStatisticResponseDTO.setId(id);
        workOfCultureStatisticResponseDTO.setImageUrl(tvSeries.getImageUrl());
        workOfCultureStatisticResponseDTO.setWorkOfCultureType(WorkOfCultureType.TVSERIES);
        workOfCultureStatisticResponseDTO.setTitle(tvSeries.getTitle());

        tvSeries.getGenres().forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    workOfCultureStatisticResponseDTO.getGenres().add(genreType);
            }
        });
        return workOfCultureStatisticResponseDTO;
    }

}
