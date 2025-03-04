package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequest {
  private UUID id;
  String givenName;
  String lastName;
  String surname;
  String email;
  String telephone;
  String username;
  Boolean isGoogleAccount;
}
