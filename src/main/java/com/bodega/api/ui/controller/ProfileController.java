package com.bodega.api.ui.controller;

import com.bodega.api.service.ProfileService;
import com.bodega.api.ui.model.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accesos")
public class ProfileController {
  private final ModelMapper mapper;
  private final ProfileService profileService;

  @GetMapping("/general")
  List<ProfileResponse> getGeneralProfiles() {
    return profileService.getGeneralProfiles()
      .stream()
      .map(profile -> mapper.map(profile, ProfileResponse.class))
      .toList();
  }
}
