package com.bodega.api.service;

import com.bodega.api.io.UserEntity;
import com.bodega.api.shared.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
  Flux<UserEntity> getUserProfiles(UUID userId);
  Mono<UserDto> registerUser(UserDto userDto);
}
