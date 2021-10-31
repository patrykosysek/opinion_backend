package pl.polsl.opinion_backend.controllers.list;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.configuration.security.jwt.JwtStatics;
import pl.polsl.opinion_backend.dtos.list.ReviewCreateDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.list.ListManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_SEEN_LIST;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/seen-lists")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class SeenListController {
    private final ListManagingService listManagingService;

    @Secured(ROLE_SEEN_LIST)
    @Operation(summary = "Add work of culture to current user seen list without review ")
    @ApiResponse(responseCode = "201", description = "Work of culture successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId) {
        listManagingService.addWorkOfCultureToSeenListWithoutReview(workOfCultureType, workOfCultureId);
    }

    @Secured(ROLE_SEEN_LIST)
    @Operation(summary = "Add work of culture to current user seen list with review ")
    @ApiResponse(responseCode = "201", description = "Work of culture successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}/with-review")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId, @RequestBody ReviewCreateDTO reviewCreateDTO) {
        listManagingService.addWorkOfCultureWithReview(workOfCultureType, workOfCultureId, reviewCreateDTO);
    }

}
