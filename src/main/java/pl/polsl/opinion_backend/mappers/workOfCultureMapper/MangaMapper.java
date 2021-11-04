package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;

@Mapper(componentModel = "spring")
public interface MangaMapper {

    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Manga manga);

}
