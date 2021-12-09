package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.TvSeriesIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.TvSeriesLikesMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.list.review.like.TvSeriesReviewLikeService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class, TvSeriesReviewLikeService.class}, componentModel = "spring")
public interface TvSeriesReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "tvSeriesReview.tvSeries.title")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "likes", source = "tvSeriesReview", qualifiedBy = TvSeriesLikesMapping.class)
    @Mapping(target = "userLike", source = "tvSeriesReview", qualifiedBy = TvSeriesIsLikedMapping.class)
    ReviewResponseDTO toReviewResponseDTO(TvSeriesReview tvSeriesReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
