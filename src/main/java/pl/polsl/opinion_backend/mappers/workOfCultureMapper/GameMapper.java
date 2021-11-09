package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeMangaGenresMapping;
import pl.polsl.opinion_backend.mappers.qualifires.GameGenresMapping;
import pl.polsl.opinion_backend.services.works.genre.GameGenreService;

@Mapper(uses = {WorkOfCultureType.class, GameGenreService.class}, componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.GAME)")
    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Game game);

    @Mapping(target = "workOfCultureType", expression = "java(WorkOfCultureType.GAME)")
    @Mapping(target = "genres", source = "genres", qualifiedBy = GameGenresMapping.class)
    WorkOfCultureInformationResponseDTO toWorkOfCultureInformationResponseDTO(Game game);

}
