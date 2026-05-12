package com.bodega.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());
        http
            .cors(cors -> cors.configurationSource(new CorsConfig()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> {
                request.requestMatchers(HttpMethod.GET, "/accesos/general").permitAll();
                request.requestMatchers(HttpMethod.GET, "/products/**").permitAll();
                request.requestMatchers(HttpMethod.GET, "/categories").permitAll();
                request.requestMatchers(HttpMethod.POST, "/user/register").permitAll();
                request.anyRequest().authenticated();
            })
            .oauth2ResourceServer(rsc -> 
                rsc.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
            );
        return http.build();
    }
}
