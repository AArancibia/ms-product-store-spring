package com.bodega.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class KeycloakProperty {
    @Value("${keycloak.baseUrl}")
    private String baseUrl;

    @Value("${keycloak.realm}")
    private String realm;
}
