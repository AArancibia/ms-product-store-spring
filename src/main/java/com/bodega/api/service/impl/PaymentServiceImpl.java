package com.bodega.api.service.impl;

import com.bodega.api.config.PayPayConfig;
import com.bodega.api.service.PaymentService;
import com.bodega.api.ui.model.request.PayPayQrCodeRequest;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.QRCodeDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

  private final PayPayConfig payPayConfig;

  @Override
  public QRCodeDetails getQRCodeDetails(PayPayQrCodeRequest request) throws ApiException {
    var apiClient = payPayConfig.apiClient();
    var qrCodeBody = payPayConfig.createQrCode();
    qrCodeBody.setAmount(request.getAmount().currency(MoneyAmount.CurrencyEnum.JPY));
    qrCodeBody.setOrderDescription(request.getDescription());
    qrCodeBody.setOrderItems(request.getOrderItems());
    var payment = new PaymentApi(apiClient);
    return payment.createQRCode(qrCodeBody);
  }
}
