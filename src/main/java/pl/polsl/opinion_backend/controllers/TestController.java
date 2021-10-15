package pl.polsl.opinion_backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_ALL;


@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/test")
public class TestController {

    @Secured(ROLE_ALL)
    @Operation(summary = "test")
    @ApiResponse(responseCode = "200", description = "test")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String testing() {
        return "OK";
    }

}
