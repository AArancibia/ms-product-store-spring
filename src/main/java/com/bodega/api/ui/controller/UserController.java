package com.bodega.api.ui.controller;

import com.bodega.api.io.UserEntity;
import com.bodega.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}/accesos")
  public Flux<UserEntity> getProfiles(@PathVariable UUID id) {
    return userService.getUserProfiles(id);
  }

}
