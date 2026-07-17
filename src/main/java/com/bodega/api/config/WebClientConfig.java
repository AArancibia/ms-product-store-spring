package com.bodega.api.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebClientConfig {

    private final KeycloakProperty keycloakProperty;

    @Bean
    public WebClient webClient() {
        return WebClient
        .builder()
        .build();
    }

    @Bean
    public WebClient webClientKeycloak() {
        return WebClient
        .builder()
        .baseUrl(keycloakProperty.getBaseUrl())
        .filter((request, next) -> {
            ServletRequestAttributes attrs =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                String token = attrs.getRequest()
                    .getHeader(HttpHeaders.AUTHORIZATION);
                ClientRequest newRequest = ClientRequest.from(request)
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
                return next.exchange(newRequest);   
            }
            return next.exchange(request);
        })
        .build();
    }
}
