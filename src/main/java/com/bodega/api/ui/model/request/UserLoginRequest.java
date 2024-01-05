package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
  private String email;
  private String password;
}
