package com.bodega.api.ui.controller;

import com.bodega.api.service.PaymentService;
import com.bodega.api.ui.model.request.PayPayQrCodeRequest;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.model.QRCodeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("payment")
@RestController
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("paypay/createQr")
  public ResponseEntity<QRCodeDetails> createQr(@RequestBody PayPayQrCodeRequest qrCodeRequest) throws ApiException {
    return new ResponseEntity<>(paymentService.getQRCodeDetails(), HttpStatus.OK);
  }
}
