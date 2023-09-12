package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
  private UUID id;
  private String username;
  private String givenName;
  private String lastName;
  private String surname;
  private String telephone;
  private String email;
  private String password;
  private String rol;
}