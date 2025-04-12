package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequest {
  private UUID id;
  private String givenName;
  private String lastName;
  private String surname;
  private String email;
  private String password;
  private String telephone;
  private String username;
  private Boolean isGoogleAccount;
  private String role;
}
