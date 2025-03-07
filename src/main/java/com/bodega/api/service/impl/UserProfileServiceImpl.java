package com.bodega.api.service.impl;

import com.bodega.api.repository.UserProfileRepository;
import com.bodega.api.service.UserProfileService;
import com.bodega.api.shared.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {
  private final UserProfileRepository userProfileRepository;
  private final ModelMapper mapper;

  @Override
  public Flux<UserProfileDto> findByUserId(UUID userId) {
    return Flux.fromIterable(userProfileRepository.findAllByUserId(userId))
      .map(userProfiles -> mapper.map(userProfiles, UserProfileDto.class));
  }

}
