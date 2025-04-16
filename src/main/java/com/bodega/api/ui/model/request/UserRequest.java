package com.bodega.api.ui.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  @NotBlank
  private String email;
  private String password;
  private String telephone;
  private String username;
  private Boolean isGoogleAccount = false;
  private String role;
}
