package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeMangaGenresMapping;
import pl.polsl.opinion_backend.services.works.genre.AnimeMangaGenreService;

@Mapper(uses = {WorkOfCultureType.class, AnimeMangaGenreService.class}, componentModel = "spring")
public interface MangaMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MANGA)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Manga manga);

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.MANGA)")
    @Mapping(target = "genres", source = "genres", qualifiedBy = AnimeMangaGenresMapping.class)
    WorkOfCultureInformationResponseDTO toWorkOfCultureInformationResponseDTO(Manga manga);

}
