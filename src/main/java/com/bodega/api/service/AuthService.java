package com.bodega.api.service;

import com.bodega.api.shared.dto.UserDto;
import reactor.core.publisher.Mono;

public interface AuthService {
  Mono<UserDto> saveUser(UserDto user);
  UserDto finByEmail(String email);
}
