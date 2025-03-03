package com.bodega.api.service.impl;

import com.bodega.api.io.UserEntity;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper mapper;

  @Override
  public Flux<UserEntity> getUserProfiles(UUID userId) {
    return Flux.just(userRepository.findById(userId).orElse(new UserEntity()));
  }

  @Override
  public Mono<UserDto> registerUser(UserDto userDto) {
    var userDb = userRepository.save(mapper.map(userDto, UserEntity.class));
    return Mono.just(mapper.map(userDb, UserDto.class));
  }
}
