package com.bodega.api.ui.controller;

import com.bodega.api.service.AuthService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.ui.model.request.UserLoginRequest;
import com.bodega.api.ui.model.request.UserRequest;
import com.bodega.api.ui.model.request.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
  private final AuthService authService;
  private final ModelMapper mapper;

  @PostMapping("/register")
  public Mono<ResponseEntity<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
    return authService.saveUser(mapper.map(userRequest, UserDto.class))
      .doOnError(throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An exception ocurred due to " + throwable.getMessage()))
      .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(userDto, UserResponse.class)));
  }

  @GetMapping("/login")
  public UserDto login(Authentication authentication) {
    log.info(String.valueOf(authentication.isAuthenticated()));
    log.info(authentication.getName());
    return authService.finByEmail(authentication.getName());
  }
}
