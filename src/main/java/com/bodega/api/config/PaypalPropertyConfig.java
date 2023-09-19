package com.bodega.api.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "paypal")
@Getter
@Setter
@ToString
public class PaypalPropertyConfig {
  private String urlV1;
  private String urlV2;
  private String clientId;
  private String secret;
}
