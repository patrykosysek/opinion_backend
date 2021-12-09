package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.MovieTvSeriesGenresMapping;
import pl.polsl.opinion_backend.services.works.genre.MovieTvSeriesGenreService;

@Mapper(uses = {WorkOfCultureType.class, MovieTvSeriesGenreService.class}, componentModel = "spring")
public interface TvSeriesMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.TVSERIES)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(TvSeries tvSeries);

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.TVSERIES)")
    @Mapping(target = "genres", source = "genres", qualifiedBy = MovieTvSeriesGenresMapping.class)
    WorkOfCultureInformationResponseDTO toWorkOfCultureInformationResponseDTO(TvSeries tvSeries);

}
