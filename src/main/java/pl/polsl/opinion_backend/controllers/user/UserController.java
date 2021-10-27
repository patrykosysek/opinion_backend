package pl.polsl.opinion_backend.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.polsl.opinion_backend.dtos.user.UserCreateDTO;
import pl.polsl.opinion_backend.dtos.user.UserResponseDTO;
import pl.polsl.opinion_backend.dtos.user.UserUpdateDTO;
import pl.polsl.opinion_backend.mappers.user.UserMapper;
import pl.polsl.opinion_backend.services.user.UserService;

import javax.validation.Valid;
import java.util.UUID;

import static pl.polsl.opinion_backend.enums.role.Roles.ROLE_ALL;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Secured(ROLE_ALL)
    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "201", description = "User successfully created")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@RequestBody @Valid UserCreateDTO dto) {
        return userMapper.toUserResponseDTO(userService.create(dto));
    }

    @Secured(ROLE_ALL)
    @Operation(summary = "Get user")
    @ApiResponse(responseCode = "200", description = "User found")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getById(@PathVariable UUID id) {
        return userMapper.toUserResponseDTO(userService.getById(id));
    }

    @Secured(ROLE_ALL)
    @Operation(summary = "Delete User")
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        userService.delete(id);
    }

    @Secured(ROLE_ALL)
    @Operation(summary = "Update user")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO update(@RequestBody @Valid UserUpdateDTO dto, @PathVariable UUID id) {
        return userMapper.toUserResponseDTO(userService.update(id, dto));
    }

    @Secured(ROLE_ALL)
    @Operation(summary = "Get users")
    @ApiResponse(responseCode = "200", description = "Users found")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponseDTO> listAll(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable).map(userMapper::toUserResponseDTO);
    }

    @Secured(ROLE_ALL)
    @Operation(summary = "Change user lock status")
    @ApiResponse(responseCode = "200", description = "User status changed")
    @PatchMapping("/lock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO changeUserLock(@PathVariable UUID id) {
        return userMapper.toUserResponseDTO(userService.changeLockStatus(id));
    }


}
