package com.bodega.api.service.impl;

import com.bodega.api.io.ProfileEntity;
import com.bodega.api.repository.ProfileRepository;
import com.bodega.api.service.ProfileService;
import com.bodega.api.shared.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
  private final ProfileRepository profileRepository;
  private final ModelMapper mapper;
  @Override
  public List<ProfileDto> getGeneralProfiles() {
    return profileRepository.findAllByGeneralIsTrue()
      .stream()
      .map(profile -> mapper.map(profile, ProfileDto.class))
      .toList();
  }
}
