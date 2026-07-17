package com.bodega.api.service.impl;

import com.bodega.api.config.KeycloakProperty;
import com.bodega.api.io.ProfileEntity;
import com.bodega.api.io.UserEntity;
import com.bodega.api.io.UserProfileEntity;
import com.bodega.api.repository.UserProfileRepository;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.ProfileService;
import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.dto.UserKeycloak;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.function.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserProfileRepository userProfileRepository;
  private final ProfileService profileService;
  private final ModelMapper mapper;
  private final WebClient webClientKeycloak;
  private final KeycloakProperty keycloakProperty;

  @Override
  public Flux<UserKeycloak> findUsers() {
    String url = "/admin/realms/"+ keycloakProperty.getRealm() + "/users";
    Predicate<UserKeycloak> predicate = user -> user.getUsername().contains("admin");
  
    return webClientKeycloak
    .get()
    .uri(url)
    .retrieve()
    .bodyToFlux(UserKeycloak.class)
    .filter(predicate.negate())
    .log();
  }

  @Override
  public Mono<UserDto> registerUser(UserDto userDto) {
    var fluxProfiles = profileService.getGeneralProfiles().map(profileDto -> mapper.map(profileDto, ProfileEntity.class));
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

  @Override
  public Mono<UserDto> findUserByUsername(String username) {
    var userDb = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found, username: " + username));
    return Mono.just(mapper.map(userDb, UserDto.class));
  }

  @Override
  public Mono<UserDto> findUserByEmail(String email) {
    var userDb = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found, email: " + email));
    return Mono.just(mapper.map(userDb, UserDto.class));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteUser(UUID id) {
    String url = "/admin/realms/"+ keycloakProperty.getRealm() + "/users/" + id;
    return webClientKeycloak
    .delete()
    .uri(url)
    .retrieve()
    .toBodilessEntity();
  }

}
