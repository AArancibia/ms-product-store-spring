package com.bodega.api.service.impl;

import com.bodega.api.io.ProfileEntity;
import com.bodega.api.io.UserEntity;
import com.bodega.api.io.UserProfileEntity;
import com.bodega.api.repository.UserProfileRepository;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.ProfileService;
import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.utils.Constants;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserProfileRepository userProfileRepository;
  private final ProfileService profileService;
  private final ModelMapper mapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<UserDto> registerUser(UserDto userDto) {
    var fluxProfiles = profileService.getGeneralProfiles().map(profileDto -> mapper.map(profileDto, ProfileEntity.class));
    if (userDto.getIsGoogleAccount()) {
      userDto.setPassword(Constants.DEFAULT_PWD);
    } else {
      userDto.setPassword(hashPassword(userDto.getPassword()));
    }
    return Mono.just(mapper.map(userDto, UserEntity.class))
      .map(userRepository::save)
      .zipWith(fluxProfiles.collectList(), (userCreated, profiles) -> {
        var userProfiles  = profiles.stream().map(profileEntity -> new UserProfileEntity(userCreated.getId(), profileEntity.getId())).toList();
        userProfileRepository.saveAll(userProfiles);
        return userCreated;
      })
      .map(userEntity -> mapper.map(userEntity, UserDto.class))
      .log();
  }

  public String hashPassword(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public Mono<UserDto> findUserByUsername(String username) {
    var userDb = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found, username: " + username));
    return Mono.just(mapper.map(userDb, UserDto.class));
  }
}
