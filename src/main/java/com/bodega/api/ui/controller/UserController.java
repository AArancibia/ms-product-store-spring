package com.bodega.api.ui.controller;

import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.dto.UserKeycloak;
import com.bodega.api.ui.model.request.UserRequest;
import com.bodega.api.ui.model.response.UserResponse;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final ModelMapper mapper;


  @GetMapping("/{email}")
  public Mono<UserResponse> findByEmail(@PathVariable String email) {
    return userService.findUserByEmail(email)
      .map(userDto -> mapper.map(userDto, UserResponse.class));
  }

  @PreAuthorize("!hasRole('USER')")
  @GetMapping("/keycloak")
  public Mono<ResponseEntity<List<UserKeycloak>>> findUsers() {
    return userService.findUsers()
    .collectList()
    .map(users -> ResponseEntity.ok().body(users));
  }

  @PreAuthorize("hasRole('SUPER_ADMIN') || hasRole('ADMINISTRATOR')")
  @DeleteMapping("{id}/delete")
  public Mono<ResponseEntity<Void>> deleteUser(
    @PathVariable("id") UUID id
  ) {
    return userService.deleteUser(id);
  }

  @PostMapping("/register")
  public Mono<UserResponse> registerUser(@RequestBody UserRequest user) {
    return userService.registerUser(mapper.map(user, UserDto.class))
    		.map(userDto -> mapper.map(userDto, UserResponse.class));
  }

}
