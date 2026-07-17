package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserKeycloak {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private Boolean enabled;
}
