package com.bodega.api.service.impl;

import com.bodega.api.repository.ProfileRepository;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.ProfileService;
import com.bodega.api.service.UserProfileService;
import com.bodega.api.shared.dto.ProfileDto;
import com.bodega.api.shared.dto.UserProfileDto;
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
public class ProfileServiceImpl implements ProfileService {
  private final ProfileRepository profileRepository;
  private final UserRepository userRepository;
  private final UserProfileService userProfileService;
  private final ModelMapper mapper;

  @Override
  public Flux<ProfileDto> getGeneralProfiles() {
    return Flux.fromIterable(profileRepository.findAllByGeneralIsTrue())
      .map(profile -> mapper.map(profile, ProfileDto.class));
  }

  @Override
  public Flux<ProfileDto> getUserProfiles(UUID userId) {
    return Mono.just(userId)
      .map(userRepository::findById)
      .flatMapMany(userEntity -> {
        if (userEntity.isPresent()) {
          return userProfileService.findByUserId(userId)
            .map(UserProfileDto::getProfile)
            .log();
        } else {
          return getGeneralProfiles();
        }
      })
      .log();
  }
}
