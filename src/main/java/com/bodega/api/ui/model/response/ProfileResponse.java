package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfileResponse {
  private UUID id;
  private String name;
  private String description;
  private String url;
  private String icon;
  private Boolean general;
}
