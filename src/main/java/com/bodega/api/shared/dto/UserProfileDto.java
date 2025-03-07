package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
  private UserDto user;
  private ProfileDto profile;
}
