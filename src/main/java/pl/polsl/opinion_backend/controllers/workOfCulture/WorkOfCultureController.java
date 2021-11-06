package pl.polsl.opinion_backend.controllers.workOfCulture;


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
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkGenreResponseDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.WorkOfCultureManagingService;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_WORK_OF_CULTURE_ALL;
import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_WORK_OF_CULTURE_RECOMMENDATION_PREFERENCE;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/work-of-culture")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class WorkOfCultureController {
    private final WorkOfCultureManagingService workOfCultureManagingService;

    @Secured(ROLE_WORK_OF_CULTURE_ALL)
    @Operation(summary = "Delete work of culture")
    @ApiResponse(responseCode = "204", description = "Work of culture successfully deleted")
    @DeleteMapping(value = "/{workOfCultureType}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID id) {
        workOfCultureManagingService.delete(workOfCultureType, id);
    }


    @Operation(summary = "Get recommendation")
    @ApiResponse(responseCode = "200", description = "Recommendation successfully returned")
    @GetMapping(value = "/{workOfCultureType}")
    @ResponseStatus(HttpStatus.OK)
    public Page<WorkOfCultureResponseDTO> getRecommendation(@RequestParam(required = false)
                                                                    Optional<GenreType> genreType,
                                                            @PathVariable
                                                                    WorkOfCultureType workOfCultureType,
                                                            @PageableDefault
                                                                    Pageable pageable) {
        return workOfCultureManagingService.getRecommendation(genreType, workOfCultureType, pageable);
    }

    @Secured(ROLE_WORK_OF_CULTURE_RECOMMENDATION_PREFERENCE)
    @Operation(summary = "Get recommendation by preference")
    @ApiResponse(responseCode = "200", description = "Recommendation successfully returned")
    @GetMapping(value = "/{workOfCultureType}/preference")
    @ResponseStatus(HttpStatus.OK)
    public Page<WorkOfCultureResponseDTO> getRecommendationByPreference(@PathVariable
                                                                                WorkOfCultureType workOfCultureType,
                                                                        @PageableDefault
                                                                                Pageable pageable) {
        return workOfCultureManagingService.getRecommendationByPreference(workOfCultureType, pageable);
    }

    @Operation(summary = "Get works of culture genres")
    @ApiResponse(responseCode = "200", description = "Genres successfully returned")
    @GetMapping(value = "/genres")
    @ResponseStatus(HttpStatus.OK)
    public Set<WorkGenreResponseDTO> getWorksGenres() {
        return workOfCultureManagingService.getWorksOfCultureGenres();
    }

    @Operation(summary = "Get work of culture filtered by title")
    @ApiResponse(responseCode = "200", description = "Work of culture successfully returned")
    @GetMapping(value = "/{workOfCultureType}/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<WorkOfCultureResponseDTO> getWorkOfCultureFilteredByTitle(@RequestParam
                                                                                  String title,
                                                                          @PathVariable
                                                                                  WorkOfCultureType workOfCultureType,
                                                                          @PageableDefault
                                                                                  Pageable pageable) {
        return workOfCultureManagingService.getWorkOfCultureFilteredByTitle(title, workOfCultureType, pageable);
    }

}
