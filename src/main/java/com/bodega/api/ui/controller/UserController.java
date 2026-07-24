package com.bodega.api.ui.controller;

import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.dto.UserKeycloak;
import com.bodega.api.ui.model.request.UserRequest;
import com.bodega.api.ui.model.response.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@Tag(name = "User", description = "Endpoint methods for users feature")
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final ModelMapper mapper;

  @Operation(summary = "Find user by email")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = "Get user information"),
  		@ApiResponse(responseCode = "400", description = "No user found")
  })
  @Parameters(value = {
		  @Parameter(in = ParameterIn.PATH, description = "Email registered of user", example = "example@hotmail.com", name = "email", required = true)
  })
  @GetMapping("/{email}")
  public Mono<UserResponse> findByEmail(@PathVariable String email) {
    return userService.findUserByEmail(email)
      .map(userDto -> mapper.map(userDto, UserResponse.class));
  }

  @Operation(summary = "List of users registered in Keycloak")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = "Get all users from keycloak"),
  		@ApiResponse(responseCode = "400", description = "No users found"),
  		@ApiResponse(responseCode = "403", description = "Forbidden resource.")
  })
  @PreAuthorize("!hasRole('USER')")
  @GetMapping("/keycloak")
  public Mono<ResponseEntity<List<UserKeycloak>>> findUsers() {
    return userService.findUsers()
    .collectList()
    .map(users -> ResponseEntity.ok().body(users));
  }

  @Operation(summary = "Delete user in Keycloak")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = ""),
  		@ApiResponse(responseCode = "400", description = "No user found"),
  		@ApiResponse(responseCode = "401", description = "Unauthorized resource"),
  		@ApiResponse(responseCode = "403", description = "Forbidden resource.")
  })
  @Parameter(in = ParameterIn.PATH, description = "Id of user keycloak", example = "8a9d0c11-0a79-4c7d-a7fa-728588ebf272", name = "id", required = true)
  @PreAuthorize("hasRole('SUPER_ADMIN') || hasRole('ADMINISTRATOR')")
  @DeleteMapping("{id}/delete")
  public Mono<ResponseEntity<Void>> deleteUser(
    @PathVariable("id") UUID id
  ) {
    return userService.deleteUser(id);
  }

  @Operation(summary = "Create user")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "201", description = "User created"),
  		@ApiResponse(responseCode = "400", description = "No user found")
  })
  @PostMapping("/register")
  public Mono<UserResponse> registerUser(@RequestBody UserRequest user) {
    return userService.registerUser(mapper.map(user, UserDto.class))
    		.map(userDto -> mapper.map(userDto, UserResponse.class));
  }

}
