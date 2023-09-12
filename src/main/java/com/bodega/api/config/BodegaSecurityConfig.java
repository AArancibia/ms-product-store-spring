package com.bodega.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class BodegaSecurityConfig {

  @Bean
  DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(requests -> requests
        .requestMatchers("/products/properties").permitAll()
        .requestMatchers("/actuator/**").permitAll()
        .requestMatchers("/auth/**").permitAll()
        .anyRequest().authenticated())
      .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

}
