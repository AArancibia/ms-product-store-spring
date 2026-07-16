package com.bodega.api.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
  private UUID id;
  private String name;
  private String description;
  private String url;
  private String icon;
  private Boolean general;
  private Boolean legacy;
}
