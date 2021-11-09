package pl.polsl.opinion_backend.controllers.workOfCulture.review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics;
import pl.polsl.opinion_backend.dtos.workOfCulture.review.ReviewResponseDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.review.ReviewManagingService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/review")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class ReviewController {
    private final ReviewManagingService reviewManagingService;

    @Operation(summary = "Get review information")
    @ApiResponse(responseCode = "200", description = "Information successfully returned")
    @GetMapping(value = "/{workOfCultureType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponseDTO getReviewInformation(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID id) {
        return reviewManagingService.getReviewInformation(workOfCultureType, id);
    }


}