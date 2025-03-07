package com.bodega.api.service;

import com.bodega.api.shared.dto.ProductDto;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SaleService {
    Mono<PaypalCreateOrderResponse> createOrder(CreateOrderRequest request);
    Mono<PaypalCreateOrderResponse> captureOrder(String paypalId, SaleRequest request);
    void buildReportSales(ReportSaleRequest saleDataReport, HttpServletResponse response);
    List<ReportSaleResponse> generateReportSales();
    Mono<SaleDto> save(SaleDto saleDto);
    Flux<ProductDto> updateProductStockByPaypalId(String paypalId);
}
