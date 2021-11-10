package pl.polsl.opinion_backend.mappers.workOfCultureMapper.discussion.answer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussionAnswer;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.services.user.UserService;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(uses = {WorkOfCultureType.class, UserService.class}, componentModel = "spring")
public interface TvSeriesAnswerDiscussionMapper {

    @Mapping(target = "author", source = "createBy", qualifiedBy = UsernameMapping.class)
    AnswerResponseDTO toAnswerResponseDTO(TvSeriesDiscussionAnswer tvSeriesDiscussionAnswer);

    default LocalDate toLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }
}
