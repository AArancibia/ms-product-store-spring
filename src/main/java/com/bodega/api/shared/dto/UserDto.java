package com.bodega.api.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private UUID id;
  private String firstName;
  private String givenName;
  private String lastName;
  private String surname;
  private String email;
  private Boolean emailVerified;
  private Boolean enabled;
  private String telephone;
  private String username;
  private Boolean isGoogleAccount;
  private List<UserSessionDto> sessions;
  private List<String> roles;

  @Getter
  @Setter
  public static class UserSessionDto {
    private String id;
    private String username;
    private String userId;
    private String ipAddress;
    private Long start;
    private Long lastAccess;
    private Boolean rememberMe;
    private Boolean transientUser;
  }
}
