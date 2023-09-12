package com.bodega.api.service.impl;

import com.bodega.api.io.UserEntity;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.AuthService;
import com.bodega.api.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
  private final UserRepository repository;
  private final ModelMapper mapper;

  @Override
  public Mono<UserDto> saveUser(UserDto user) {
    var users = repository.findByEmail(user.getEmail());
    if (users.isEmpty()) {
      var userDb = mapper.map(user, UserEntity.class);
      return Mono.just(userDb)
        .map(repository::save)
        .map(userEntity -> mapper.map(userEntity, UserDto.class))
        .log();
    }
    return Mono.error(new RuntimeException("User already register with the email: " + user.getEmail()));
  }
}
