package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

@Mapper(uses = {WorkOfCultureType.class}, componentModel = "spring")
public interface MangaMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MANGA)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Manga manga);

}
