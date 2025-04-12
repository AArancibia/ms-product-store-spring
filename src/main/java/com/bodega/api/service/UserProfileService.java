package com.bodega.api.service;

import com.bodega.api.shared.dto.UserProfileDto;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UserProfileService {
  Flux<UserProfileDto> findByUserId(UUID userId);
}
