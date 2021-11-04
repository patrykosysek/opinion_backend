package pl.polsl.opinion_backend.mappers.workOfCultureMapper;

import org.mapstruct.Mapper;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {

    WorkOfCultureResponseDTO toWorkOfCultureResponseDTO(Game game);

}
