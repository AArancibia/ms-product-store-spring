package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response for User")
@Getter
@Setter
public class UserResponse {
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
  private List<UserSessionResponse> sessions;
  private List<String> roles;

  @Getter
  @Setter
  public static class UserSessionResponse {
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
