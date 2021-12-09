package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.MangaIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.MangaLikesMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.list.review.like.MangaReviewLikeService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class, MangaReviewLikeService.class}, componentModel = "spring")
public interface MangaReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "mangaReview.manga.title")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "likes", source = "mangaReview", qualifiedBy = MangaLikesMapping.class)
    @Mapping(target = "userLike", source = "mangaReview", qualifiedBy = MangaIsLikedMapping.class)
    ReviewResponseDTO toReviewResponseDTO(MangaReview mangaReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
