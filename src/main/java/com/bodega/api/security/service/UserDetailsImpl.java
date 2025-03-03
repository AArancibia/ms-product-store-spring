package com.bodega.api.security.service;

import com.bodega.api.io.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

  private UUID id;
  private String username;
  private String email;

  @JsonIgnore
  private String password;

  private boolean is2faEnabled;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(UUID id, String username, String email, String password, boolean is2faEnabled, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.is2faEnabled = is2faEnabled;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(UserEntity user) {
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
    return new UserDetailsImpl(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getPassword(),
      false,
      List.of(grantedAuthority)
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return "";
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
