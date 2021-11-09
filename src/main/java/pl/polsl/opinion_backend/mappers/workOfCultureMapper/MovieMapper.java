package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeMangaGenresMapping;
import pl.polsl.opinion_backend.mappers.qualifires.MovieTvSeriesGenresMapping;
import pl.polsl.opinion_backend.services.works.genre.MovieTvSeriesGenreService;

@Mapper(uses = {WorkOfCultureType.class, MovieTvSeriesGenreService.class}, componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MOVIE)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Movie movie);

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MOVIE)")
    @Mapping(target = "genres", source = "genres", qualifiedBy = MovieTvSeriesGenresMapping.class)
    WorkOfCultureInformationResponseDTO toWorkOfCultureInformationResponseDTO(Movie movie);


}
