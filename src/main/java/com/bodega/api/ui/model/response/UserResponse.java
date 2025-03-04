package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {
  private UUID id;
  String givenName;
  String lastName;
  String surname;
  String telephone;
}
