package com.bodega.api.ui.controller;

import com.bodega.api.service.AuthService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.ui.model.request.UserRequest;
import com.bodega.api.ui.model.request.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/auth")
@RestController
public class AuthController {
  @Autowired
  private AuthService authService;
  @Autowired
  private ModelMapper mapper;

  @PostMapping("/register")
  public Mono<ResponseEntity<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
    return authService.saveUser(mapper.map(userRequest, UserDto.class))
      .doOnError(throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An exception ocurred due to " + throwable.getMessage()))
      .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(userDto, UserResponse.class)));
  }
}
