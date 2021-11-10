package pl.polsl.opinion_backend.mappers.workOfCultureMapper.discussion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class}, componentModel = "spring")
public interface MovieDiscussionMapper {

    @Mapping(target = "title", source = "movieDiscussion.movie.title")
    DiscussionResponseDTO toDiscussionResponseDTO(MovieDiscussion movieDiscussion);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

    @Mapping(target = "title", source = "movieDiscussion.movie.title")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "author", source = "movieDiscussion.createBy", qualifiedBy = UsernameMapping.class)
    DiscussionInformationResponseDTO toDiscussionInformationResponseDTO(MovieDiscussion movieDiscussion, Page<AnswerResponseDTO> answers);

}
