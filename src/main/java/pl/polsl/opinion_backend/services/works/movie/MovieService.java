package pl.polsl.opinion_backend.services.works.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.StatisticResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureStatisticResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.works.movie.MovieRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MOVIE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MovieService extends WorkOfCultureService<Movie, MovieRepository> {

    @Override
    public Movie getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MOVIE_NOT_FOUND));
    }

    public WorkOfCultureStatisticResponseDTO getStatistic(UUID id) {
        Movie movie = getById(id);
        WorkOfCultureStatisticResponseDTO workOfCultureStatisticResponseDTO = new WorkOfCultureStatisticResponseDTO();
        workOfCultureStatisticResponseDTO.setStatisticResponseDTO(new StatisticResponseDTO());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setDiscussionCount(movie.getDiscussions().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setReviewCount(movie.getReviews().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setSeenListCount(movie.getMovieSeenLists().size());
        workOfCultureStatisticResponseDTO.getStatisticResponseDTO().setWatchListCount(movie.getMovieWatchLists().size());

        workOfCultureStatisticResponseDTO.setId(id);
        workOfCultureStatisticResponseDTO.setImageUrl(movie.getImageUrl());
        workOfCultureStatisticResponseDTO.setWorkOfCultureType(WorkOfCultureType.MOVIE);
        workOfCultureStatisticResponseDTO.setTitle(movie.getTitle());


        movie.getGenres().forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    workOfCultureStatisticResponseDTO.getGenres().add(genreType);
            }
        });
        return workOfCultureStatisticResponseDTO;
    }

}
