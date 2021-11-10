package pl.polsl.opinion_backend.mappers.workOfCultureMapper.review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.MovieIsLikedMapping;
import pl.polsl.opinion_backend.mappers.qualifires.MovieLikesMapping;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.list.review.like.MovieReviewLikeService;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class, MovieReviewLikeService.class}, componentModel = "spring")
public interface MovieReviewMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "title", source = "movieReview.movie.title")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "likes", source = "movieReview", qualifiedBy = MovieLikesMapping.class)
    @Mapping(target = "userLike", source = "movieReview", qualifiedBy = MovieIsLikedMapping.class)
    ReviewResponseDTO toReviewResponseDTO(MovieReview movieReview);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
