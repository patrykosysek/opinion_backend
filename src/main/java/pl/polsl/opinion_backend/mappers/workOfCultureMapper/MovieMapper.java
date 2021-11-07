package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

@Mapper(uses = {WorkOfCultureType.class}, componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MOVIE)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Movie movie);

}
