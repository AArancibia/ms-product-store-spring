package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfileResponse {
  private UUID id;
  private String url;
  private String icon;
  private String description;
  private Boolean general;
}
