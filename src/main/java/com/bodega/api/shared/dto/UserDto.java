package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
  private UUID id;
  String givenName;
  String lastName;
  String surname;
  String email;
  String telephone;
}
