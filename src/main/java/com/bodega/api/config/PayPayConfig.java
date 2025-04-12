package com.bodega.api.config;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.model.QRCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class PayPayConfig {

  private final PayPayProperties payPayProperties;

  @Bean
  public ApiClient apiClient() throws ApiException {
    ApiClient apiClient = new jp.ne.paypay.Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false);
    apiClient.setApiKey(payPayProperties.getPaypayApiKey());
    apiClient.setApiSecretKey(payPayProperties.getPaypayApiSecretKey());
    apiClient.setAssumeMerchant(payPayProperties.getMerchantId());
    return apiClient;
  }

  @Bean
  public QRCode createQrCode() {
    QRCode qrCode = new QRCode();
    qrCode.setMerchantPaymentId(UUID.randomUUID().toString());
    qrCode.setCodeType("ORDER_QR");
    return qrCode;
  }
}
