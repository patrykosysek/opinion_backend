package pl.polsl.opinion_backend.controllers.workOfCulture.discussion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.*;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.discussion.DiscussionManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_DISCUSSION;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/discussion")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class DiscussionController {
    private final DiscussionManagingService discussionManagingService;

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Add discussion to pointed work of culture")
    @ApiResponse(responseCode = "201", description = "Discussion successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscussionResponseDTO create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId, @RequestBody DiscussionCreateDTO discussionCreateDTO) {
        return discussionManagingService.addDiscussion(workOfCultureType, workOfCultureId, discussionCreateDTO);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Add answer to discussion")
    @ApiResponse(responseCode = "201", description = "Answer successfully added")
    @PostMapping("/{workOfCultureType}/{discussionId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerResponseDTO createAnswer(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID discussionId, @RequestBody AnswerCreateDTO answerCreateDTO) {
        return discussionManagingService.addAnswer(workOfCultureType, discussionId, answerCreateDTO);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateBy(@PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateBy(workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions ordered by date desc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/desc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateByDesc(@PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateByDesc(workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions ordered by date asc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/asc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateByAsc(@PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateByAsc(workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions filtered by topic and ordered by date desc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/topic-desc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopicDesc(@PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateByAndTopicDesc(workOfCultureType, topic, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions filtered by topic and ordered by date asc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/topic-asc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopicAsc(@PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateByAndTopicAsc(workOfCultureType, topic, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get current user discussions filtered by topic")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/topic")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByCreateByAndTopic(@PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByCreateByAndTopic(workOfCultureType, topic, pageable);
    }


    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture discussions")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureId(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureId(workOfCultureId, workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture  discussions ordered by date desc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}/desc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdDesc(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureIdDesc(workOfCultureId, workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture discussions ordered by date asc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}/asc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAsc(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureIdAsc(workOfCultureId, workOfCultureType, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture discussions filtered by topic and ordered by date desc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}/topic-desc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopicDesc(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureIdAndTopicDesc(workOfCultureId, workOfCultureType, topic, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture  discussions filtered by topic and ordered by date asc")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}/topic-asc")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopicAsc(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureIdAndTopicAsc(workOfCultureId, workOfCultureType, topic, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Pointed work of culture  discussions filtered by topic")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{workOfCultureId}/topic")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscussionResponseDTO> getDiscussionByWorkOfCultureIdAndTopic(@PathVariable UUID workOfCultureId, @PathVariable WorkOfCultureType workOfCultureType, @RequestParam String topic, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionByWorkOfCultureIdAndTopic(workOfCultureId, workOfCultureType, topic, pageable);
    }

    @Secured(ROLE_DISCUSSION)
    @Operation(summary = "Get specific information about pointed discussion")
    @ApiResponse(responseCode = "201", description = "Discussions successfully returned")
    @GetMapping("/{workOfCultureType}/{id}/information")
    @ResponseStatus(HttpStatus.OK)
    public DiscussionInformationResponseDTO getDiscussionInformation(@PathVariable UUID id, @PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return discussionManagingService.getDiscussionInformation(id, workOfCultureType, pageable);
    }

}
