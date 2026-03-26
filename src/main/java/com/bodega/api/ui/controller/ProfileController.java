package com.bodega.api.ui.controller;

import com.bodega.api.service.ProfileService;
import com.bodega.api.ui.model.response.ProfileResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/accesos")
public class ProfileController {
  private final ModelMapper mapper;
  private final ProfileService profileService;

  @GetMapping("/general")
  Flux<ProfileResponse> getGeneralProfiles() {
    return profileService.getGeneralProfiles()
      .map(profile -> mapper.map(profile, ProfileResponse.class));
  }

  @PreAuthorize("#id.toString() == #jwt.subject or hasRole('ADMINISTRATOR')")
  @GetMapping
  public Flux<ProfileResponse> getProfiles(
    @RequestParam(name = "usuarioId", required = true) UUID id,
    @AuthenticationPrincipal Jwt jwt
    ) {
    return profileService
      .getUserProfiles(id)
      .map(userProfile -> mapper.map(userProfile, ProfileResponse.class))
      .log();
  }
}
