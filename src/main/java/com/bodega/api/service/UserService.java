package com.bodega.api.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.dto.UserKeycloak;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
  Mono<UserDto> registerUser(UserDto userDto);
  Mono<UserDto> findUserByUsername(String username);
  Mono<UserDto> findUserByEmail(String email);
  Flux<UserKeycloak> findUsers();
  Mono<ResponseEntity<Void>> deleteUser(UUID id);
}
