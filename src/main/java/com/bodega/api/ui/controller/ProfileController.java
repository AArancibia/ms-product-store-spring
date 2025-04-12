package com.bodega.api.ui.controller;

import com.bodega.api.service.ProfileService;
import com.bodega.api.ui.model.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

  @GetMapping
  public Flux<ProfileResponse> getProfiles(@RequestParam(name = "usuarioId", required = false, defaultValue = "") UUID id) {
    log.info("ProfileController");
    return Mono.justOrEmpty(id)
      .hasElement()
      .flatMapMany(userId -> {
        if (userId) {
          return profileService.getUserProfiles(id);
        } else {
          return profileService.getGeneralProfiles();
        }
      })
      .map(userProfile -> mapper.map(userProfile, ProfileResponse.class))
      .log();
  }
}
