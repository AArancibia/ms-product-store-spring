package com.bodega.api.service;

import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;

public interface PaymentService {
  QRCodeDetails getQRCodeDetails() throws ApiException;
}
