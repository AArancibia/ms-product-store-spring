package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfileDto {
  private UUID id;
  private String url;
  private String icon;
  private String description;
  private Boolean general;
}
