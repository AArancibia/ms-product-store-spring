package com.bodega.api.service;

import com.bodega.api.shared.dto.ProfileDto;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface ProfileService {
  Flux<ProfileDto> getGeneralProfiles();
  Flux<ProfileDto> getUserProfiles(UUID userId);
}
