package com.bodega.api.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "locale")
@Getter @Setter @ToString
public class BodegaServiceConfig {
  private String language;
  private String country;
}
