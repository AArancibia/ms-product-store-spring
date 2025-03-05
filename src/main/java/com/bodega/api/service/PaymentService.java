package com.bodega.api.service;

import com.bodega.api.ui.model.request.PayPayQrCodeRequest;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.model.QRCodeDetails;

public interface PaymentService {
  QRCodeDetails getQRCodeDetails(PayPayQrCodeRequest request) throws ApiException;
}
