package com.bodega.api.service;

import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    void buildReportSales(ReportSaleRequest saleDataReport, HttpServletResponse response);
    List<ReportSaleResponse> generateReportSales();
    Mono<SaleDto> save(SaleDto saleDto);
    Flux<SaleDto> getSalesByUserId(UUID userId);
}
