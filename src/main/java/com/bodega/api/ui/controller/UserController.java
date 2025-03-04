package com.bodega.api.ui.controller;

import com.bodega.api.io.UserEntity;
import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.ui.model.request.UserRequest;
import com.bodega.api.ui.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final ModelMapper mapper;

  @GetMapping("/{id}/accesos")
  public Flux<UserEntity> getProfiles(@PathVariable UUID id) {
    return userService.getUserProfiles(id);
  }

  @PostMapping("/{username}")
  public Mono<UserResponse> registerUser(@PathVariable String username) {
    return userService.findUserByUsername(username)
      .map(userDto -> mapper.map(userDto, UserResponse.class));
  }

  @PostMapping("/register")
  public Mono<UserResponse> registerUser(@RequestBody UserRequest user) {
    return userService.registerUser(mapper.map(user, UserDto.class))
      .map(userDto -> mapper.map(userDto, UserResponse.class));
  }

}
