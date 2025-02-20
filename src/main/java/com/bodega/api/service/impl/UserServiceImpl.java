package com.bodega.api.service.impl;

import com.bodega.api.io.UserEntity;
// import com.bodega.api.repository.UserProfileRepository;
import com.bodega.api.repository.ProfileRepository;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  @Override
  public Flux<UserEntity> getUserProfiles(UUID userId) {
    return Flux.just(userRepository.findById(UUID.fromString("5452ae64-59ad-4ccd-bef4-7424293baee5")).orElse(new UserEntity()));
  }
}
