package pl.polsl.opinion_backend.controllers.workOfCulture;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.WorkOfCultureDiscussionManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_DISCUSSION;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/discussion")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class WorkOfCultureDiscussionController {
    private final WorkOfCultureDiscussionManagingService workOfCultureDiscussionManagingService;

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Add discussion to pointed work of culture")
    @ApiResponse(responseCode = "201", description = "Discussion successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId, @RequestBody DiscussionCreateDTO discussionCreateDTO) {
        workOfCultureDiscussionManagingService.addDiscussion(workOfCultureType, workOfCultureId, discussionCreateDTO);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Add answer to discussion")
    @ApiResponse(responseCode = "201", description = "Answer successfully added")
    @PostMapping("/{workOfCultureType}/{discussionId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnswer(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID discussionId, @RequestBody AnswerCreateDTO answerCreateDTO) {
        workOfCultureDiscussionManagingService.addAnswer(workOfCultureType, discussionId, answerCreateDTO);
    }

}
