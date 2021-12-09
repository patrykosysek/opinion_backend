package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeMangaGenresMapping;
import pl.polsl.opinion_backend.services.works.genre.AnimeMangaGenreService;

@Mapper(uses = {WorkOfCultureType.class, AnimeMangaGenreService.class}, componentModel = "spring")
public interface AnimeMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.ANIME)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Anime anime);

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.ANIME)")
    @Mapping(target = "genres", source = "genres", qualifiedBy = AnimeMangaGenresMapping.class)
    WorkOfCultureInformationResponseDTO toWorkOfCultureInformationResponseDTO(Anime anime);

}
