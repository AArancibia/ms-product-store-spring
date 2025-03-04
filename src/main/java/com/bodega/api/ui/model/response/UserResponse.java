package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {
  private UUID id;
  private String givenName;
  private String lastName;
  private String surname;
  private String email;
  private String telephone;
  private String username;
  private Boolean isGoogleAccount;
}
