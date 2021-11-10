package pl.polsl.opinion_backend.controllers.workOfCulture.review;

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
import pl.polsl.opinion_backend.dtos.list.ReviewCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.list.ListManagingService;
import pl.polsl.opinion_backend.services.works.review.ReviewManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_REVIEW_LIST;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/review")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class ReviewController {
    private final ReviewManagingService reviewManagingService;
    private final ListManagingService listManagingService;

    @Operation(summary = "Get review information")
    @ApiResponse(responseCode = "200", description = "Information successfully returned")
    @GetMapping(value = "/{workOfCultureType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponseDTO getReviewInformation(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID id) {
        return reviewManagingService.getReviewInformation(workOfCultureType, id);
    }

    @Secured(ROLE_REVIEW_LIST)
    @Operation(summary = "Get work of culture from current user review list ")
    @ApiResponse(responseCode = "200", description = "Work of culture successfully returned")
    @GetMapping("/{workOfCultureType}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewResponseDTO> getReviewList(@PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return listManagingService.getReviewListWorks(workOfCultureType, pageable);
    }

    @Secured(ROLE_REVIEW_LIST)
    @Operation(summary = "Add work of culture to current user review list")
    @ApiResponse(responseCode = "201", description = "Work of culture successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId, @RequestBody ReviewCreateDTO reviewCreateDTO) {
        listManagingService.addWorkOfCultureWithReview(workOfCultureType, workOfCultureId, reviewCreateDTO);
    }

}