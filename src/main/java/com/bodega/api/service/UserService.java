package com.bodega.api.service;

import com.bodega.api.shared.dto.UserDto;
import reactor.core.publisher.Mono;


public interface UserService {
  Mono<UserDto> registerUser(UserDto userDto);
  Mono<UserDto> findUserByUsername(String username);
}
