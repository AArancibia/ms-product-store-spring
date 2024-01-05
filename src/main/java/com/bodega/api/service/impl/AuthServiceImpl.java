package com.bodega.api.service.impl;

import com.bodega.api.io.UserEntity;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.AuthService;
import com.bodega.api.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
  private final UserRepository repository;
  private final ModelMapper mapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<UserDto> saveUser(UserDto user) {
    var users = repository.findByEmail(user.getEmail());
    if (users.isEmpty()) {
      var userDb = mapper.map(user, UserEntity.class);
      userDb.setPassword(passwordEncoder.encode(user.getPassword()));
      return Mono.just(userDb)
        .map(repository::save)
        .map(userEntity -> mapper.map(userEntity, UserDto.class))
        .log();
    }
    return Mono.error(new RuntimeException("User already register with the email: " + user.getEmail()));
  }

  @Override
  public UserDto finByEmail(String email) {
    var userDb= repository.findByEmail(email);
    if (userDb.isEmpty()) {
      throw new UsernameNotFoundException("Usuario con email no registrado");
    }
    return mapper.map(userDb.get(0), UserDto.class);
  }
}
