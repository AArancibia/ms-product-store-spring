package com.bodega.api.config;

import com.bodega.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class BodegaAuthenticationProvider implements AuthenticationProvider {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info(authentication.getName());
    String username = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    var user = repository.findByEmail(username);
    if (!user.isEmpty()) {
      var userDb = user.get(0);
      if (passwordEncoder.matches(pwd, userDb.getPassword())) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDb.getRol()));
        return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
      } else {
        throw new BadCredentialsException("Invalid password!");
      }
    }
    throw new BadCredentialsException("No user registered with this details!");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
