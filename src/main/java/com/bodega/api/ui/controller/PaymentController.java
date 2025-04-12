package com.bodega.api.ui.controller;

import com.bodega.api.service.PaymentService;
import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.PayPayQrCodeRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.SaleResponse;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.model.QRCodeDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("payment")
@RestController
public class PaymentController {

  private final PaymentService paymentService;
  private final SaleService saleService;
  private final ModelMapper mapper;

  @PostMapping("paypay/createQr")
  public ResponseEntity<QRCodeDetails> createQr(@RequestBody PayPayQrCodeRequest qrCodeRequest) throws ApiException {
    return new ResponseEntity<>(paymentService.getQRCodeDetails(qrCodeRequest), HttpStatus.OK);
  }

  @PostMapping("google")
  public Mono<SaleResponse> captureOrder(@RequestBody SaleRequest request) {
    var sale = mapper.map(request, SaleDto.class);
    return saleService.save(sale)
      .flatMap(savedSale -> saleService.updateProductStockByCode(savedSale.getCode()).collectList())
      .map(updatedProducts -> mapper.map(sale, SaleResponse.class));
  }
}
