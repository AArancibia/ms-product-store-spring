package com.bodega.api.service.client;

import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@FeignClient("sale")
public interface SaleFeignClient {
  @PostMapping("/api/sale/order")
  PaypalCreateOrderResponse createOrder(@RequestBody CreateOrderRequest request);
}
