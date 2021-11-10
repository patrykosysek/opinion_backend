package pl.polsl.opinion_backend.controllers.list;

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
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureResponseDTO;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.list.ListManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_WATCH_LIST;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/watch-lists")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class WatchListController {
    private final ListManagingService listManagingService;

    @Secured(ROLE_WATCH_LIST)
    @Operation(summary = "Add work of culture to current user watch list ")
    @ApiResponse(responseCode = "201", description = "Work of culture successfully added")
    @PostMapping("/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId) {
        listManagingService.addWorkOfCultureToWatchList(workOfCultureType, workOfCultureId);
    }

    @Secured(ROLE_WATCH_LIST)
    @Operation(summary = "Remove work of culture from current user watch list")
    @ApiResponse(responseCode = "200", description = "Work of culture successfully removed")
    @DeleteMapping(value = "/{workOfCultureType}/{workOfCultureId}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID workOfCultureId) {
        listManagingService.removeWorkOfCulture(workOfCultureType, workOfCultureId);
    }

    @Secured(ROLE_WATCH_LIST)
    @Operation(summary = "Get work of culture from current user watch list ")
    @ApiResponse(responseCode = "200", description = "Work of culture successfully returned")
    @GetMapping("/{workOfCultureType}")
    @ResponseStatus(HttpStatus.OK)
    public Page<WorkOfCultureResponseDTO> getWatchList(@PathVariable WorkOfCultureType workOfCultureType, @PageableDefault Pageable pageable) {
        return listManagingService.getWatchListWorks(workOfCultureType, pageable);
    }

}
