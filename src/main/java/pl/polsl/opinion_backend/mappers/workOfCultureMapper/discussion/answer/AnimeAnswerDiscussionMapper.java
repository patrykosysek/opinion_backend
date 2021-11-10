package pl.polsl.opinion_backend.mappers.workOfCultureMapper.discussion.answer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionInformationResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussionAnswer;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameDiscussion;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class}, componentModel = "spring")
public interface AnimeAnswerDiscussionMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    @Mapping(target = "id",source = "id")
    AnswerResponseDTO toAnswerResponseDTO(AnimeDiscussionAnswer animeDiscussionAnswer);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

}
