package com.bodega.api.config;

import com.bodega.api.filter.CsrfFilterCookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class BodegaSecurityConfig {

  @Bean
  DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

    http
      .securityContext()
      .requireExplicitSave(false)
      .and()
      .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
      .cors()
        .configurationSource(new CorsConfigurationSource() {
          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
            corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
            corsConfiguration.setMaxAge(360L);
            return corsConfiguration;
          }
        })
      .and()
      // .csrf(AbstractHttpConfigurer::disable)
      .csrf(httpSecurityCsrfConfigurer -> {
        httpSecurityCsrfConfigurer
          .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
          // .ignoringRequestMatchers("/products/")
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      })
      .addFilterAfter(new CsrfFilterCookie(), BasicAuthenticationFilter.class)
      .authorizeHttpRequests(requests -> requests
        .requestMatchers("/products/properties").permitAll()
        .requestMatchers("/products/**").permitAll()
        .requestMatchers("/actuator/**").permitAll()
        .requestMatchers("/auth/register").permitAll()
        .requestMatchers("/user").authenticated()
        .anyRequest().authenticated())
      .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
