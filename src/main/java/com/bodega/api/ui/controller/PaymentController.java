package com.bodega.api.ui.controller;

import com.bodega.api.service.PaymentService;
import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.PayPayQrCodeRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.SaleResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.model.QRCodeDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Payment", description = "Endpoint methods for payment feature")
@RequiredArgsConstructor
@RequestMapping("payment")
@RestController
public class PaymentController {

  private final PaymentService paymentService;
  private final SaleService saleService;
  private final ModelMapper mapper;

  @Operation(summary = "Create QR code for Paypay paymetn")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = "Get Paypay QR code"),
  })
  @PostMapping("paypay/createQr")
  public ResponseEntity<QRCodeDetails> createQr(@RequestBody PayPayQrCodeRequest qrCodeRequest) throws ApiException {
    return new ResponseEntity<>(paymentService.getQRCodeDetails(qrCodeRequest), HttpStatus.OK);
  }

  @Operation(summary = "Save payment with Google payment")
  @ApiResponses(value = {
  		@ApiResponse(responseCode = "200", description = "Saved payment"),
  })
  @PostMapping("google")
  public Mono<SaleResponse> captureOrder(@Valid @RequestBody SaleRequest request) {
    var sale = mapper.map(request, SaleDto.class);
    return saleService.save(sale)
      .flatMap(savedSale -> saleService.updateProductStockByCode(savedSale.getCode()).collectList())
      .map(updatedProducts -> mapper.map(sale, SaleResponse.class));
  }
}
