package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.GameIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.GameLikesMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.list.review.like.GameReviewLikeService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class, GameReviewLikeService.class}, componentModel = "spring")
public interface GameReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "gameReview.game.title")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "likes", source = "gameReview", qualifiedBy = GameLikesMapping.class)
    @Mapping(target = "userLike", source = "gameReview", qualifiedBy = GameIsLikedMapping.class)
    ReviewResponseDTO toReviewResponseDTO(GameReview gameReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
