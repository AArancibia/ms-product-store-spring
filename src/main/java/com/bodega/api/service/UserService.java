package com.bodega.api.service;

import com.bodega.api.io.UserEntity;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UserService {
  Flux<UserEntity> getUserProfiles(UUID userId);
}
