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
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.services.works.WorkOfCultureManagingService;

import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_ALL;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/work-of-culture")
@SecurityRequirement(name = JwtStatics.SECURITY_SCHEME_NAME)
public class WorkOfCultureController {
    private final WorkOfCultureManagingService workOfCultureManagingService;

    @Secured(ROLE_ALL)
    @Operation(summary = "Delete work of culture")
    @ApiResponse(responseCode = "204", description = "Work of culture successfully deleted")
    @DeleteMapping(value = "/{workOfCultureType}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable WorkOfCultureType workOfCultureType, @PathVariable UUID id) {
        workOfCultureManagingService.delete(workOfCultureType,id);
    }


}
