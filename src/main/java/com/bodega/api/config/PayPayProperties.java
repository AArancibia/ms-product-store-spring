package com.bodega.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PayPayProperties {
  @Value("${paypay.api.key}")
  private String paypayApiKey;

  @Value("${paypay.api.secretKey}")
  private String paypayApiSecretKey;

  @Value("${paypay.api.merchantId}")
  private String merchantId;
}
