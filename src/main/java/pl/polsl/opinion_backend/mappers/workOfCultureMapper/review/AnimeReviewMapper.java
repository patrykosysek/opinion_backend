package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.AnimeLikesMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.list.review.like.AnimeReviewLikeService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class, AnimeReviewLikeService.class}, componentModel = "spring")
public interface AnimeReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "animeReview.anime.title")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "likes", source = "animeReview", qualifiedBy = AnimeLikesMapping.class)
    @Mapping(target = "userLike", source = "animeReview", qualifiedBy = AnimeIsLikedMapping.class)
    ReviewResponseDTO toReviewResponseDTO(AnimeReview animeReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
