package com.bodega.api.service.impl;

import com.bodega.api.config.KeycloakProperty;
import com.bodega.api.exception.ForbiddenException;
import com.bodega.api.io.UserEntity;
import com.bodega.api.repository.UserRepository;
import com.bodega.api.service.UserService;
import com.bodega.api.shared.dto.RealMappingDto;
import com.bodega.api.shared.dto.UserDto;
import com.bodega.api.shared.dto.UserKeycloak;
import com.bodega.api.shared.utils.Constants;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final WebClient webClientKeycloak;
  private final KeycloakProperty keycloakProperty;

  @Transactional(readOnly = true)
  @Override
  public Flux<UserDto> findUsers() {
    String url = "/admin/realms/"+ keycloakProperty.getRealm() + "/users";
    Predicate<UserKeycloak> predicate = user -> user.getEmail().contains("admin@do.not.edit");

    JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    var token = authentication.getToken().getTokenValue();
    return webClientKeycloak
    .get()
    .uri(url)
    .retrieve()
    .bodyToFlux(UserKeycloak.class)
    .filter(predicate.negate())
    .flatMap(user -> {

      var sessionsFlux = this.getUserSessions(user.getId(), token);
      var roleFlux = this.getUserRoles(user.getId(), token);

      return Mono.zip(sessionsFlux, roleFlux)
      .map(tuple -> UserDto.builder()
                .id(UUID.fromString(user.getId()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .emailVerified(user.getEmailVerified())
                .enabled(user.getEnabled())
                .username(user.getUsername())
                .sessions(tuple.getT1())
                .roles(tuple.getT2())
                .build());
    })
    .log();
  }

  public Mono<List<UserDto.UserSessionDto>> getUserSessions(String userId, String token) {
    String urlSessions = "/admin/realms/"+ keycloakProperty.getRealm() + "/users/" + userId + "/sessions";
    return webClientKeycloak
    .get()
    .uri(urlSessions)
    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
    .retrieve()
    .bodyToFlux(UserDto.UserSessionDto.class)
    .collectList()
    .log();
  }

  public Mono<List<String>> getUserRoles(String userId, String token) {
    String urlRoles = "/admin/realms/"+ keycloakProperty.getRealm() + "/users/" + userId + "/role-mappings";
    return webClientKeycloak
    .get()
    .uri(urlRoles)
    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
    .retrieve()
    .bodyToMono(RealMappingDto.class)
    .map(realMappingDto -> realMappingDto.getRealmMappings())
    .defaultIfEmpty(List.of())
    .flatMapMany(roleMappings -> 
      Flux.fromIterable(roleMappings)
        .filter(roleMapping -> !roleMapping.getName().equals("default-roles-portfoliodev"))
        .map(roleMapping -> roleMapping.getName()))
        .collectList()
    .log();
  }


  @Override
  public Mono<UserDto> registerUser(UserDto userDto) {
    return Mono.just(mapper.map(userDto, UserEntity.class))
      .map(userRepository::save)
      .map(userEntity -> mapper.map(userEntity, UserDto.class))
      .log();
  }

  @Transactional(readOnly = true)
  @Override
  public Mono<UserDto> findUserByUsername(String username) {
    var userDb = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found, username: " + username));
    return Mono.just(mapper.map(userDb, UserDto.class));
  }

  @Transactional(readOnly = true)
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
    .onStatus(HttpStatusCode::is4xxClientError,  clientResponse -> {
    	if (clientResponse.statusCode().equals(HttpStatus.FORBIDDEN)) {
    		throw new ForbiddenException(Constants.HTTP_FORBIDDEN_MESSAGE);
    	}
    	return Mono.error(new RuntimeException(Constants.HTTP_DEFAULT_MESSAGE));
    })
    .toBodilessEntity();
  }

}
