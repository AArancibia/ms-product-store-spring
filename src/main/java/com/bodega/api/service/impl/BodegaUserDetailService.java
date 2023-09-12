package com.bodega.api.service.impl;

import com.bodega.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BodegaUserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String userName;
    String password;
    List<GrantedAuthority> authorities = new ArrayList<>();
    var users = userRepository.findByEmail(username);
    if (users.isEmpty()) {
      throw new UsernameNotFoundException("User details not found for the user: " + username);
    } else {
      userName = users.get(0).getUsername();
      password = users.get(0).getPassword();
      authorities.add(new SimpleGrantedAuthority(users.get(0).getRol()));
    }
    return new User(userName, password, authorities);
  }
}
