package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class}, componentModel = "spring")
public interface GameReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "gameReview.game.title")
    @Mapping(target = "id", source = "id")
    ReviewResponseDTO toReviewResponseDTO(GameReview gameReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
