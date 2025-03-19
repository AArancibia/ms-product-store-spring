package com.bodega.api.ui.controller;

import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import com.bodega.api.ui.model.response.SaleResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sale")
public class SaleController {
    private final SaleService service;
    private final ModelMapper mapper;

    @GetMapping("/user/{id}")
    public Flux<SaleResponse> getSalesByUser(@PathVariable("id") UUID userId) {
        return service.getSalesByUser(userId)
          .map(sale -> mapper.map(sale, SaleResponse.class));
    }

    @PostMapping("order")
    public Mono<PaypalCreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @PostMapping("order/{id}/capture")
    public Mono<PaypalCreateOrderResponse> captureOrder(@PathVariable("id") String paypalId, @RequestBody SaleRequest request) {
        return service.captureOrder(paypalId, request)
          .flatMap(paypalCreateOrderResponse -> service.updateProductStockByPaypalId(paypalId)
            .collectList()
            .single()
            .map(productDto -> paypalCreateOrderResponse));
    }

    @PostMapping("reporteFile")
    public void generateReportPDF(
      @RequestBody() ReportSaleRequest saleDataReport, HttpServletResponse response,
      @RequestParam("contentType") String contentType
    ) {
        log.info("contentType: {}", contentType);
        response.setContentType("application/" + contentType);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        this.service.buildReportSales(saleDataReport, response);
    }

    @PostMapping("reporte")
    public List<ReportSaleResponse> generateReport() {
        return this.service.generateReportSales();
    }

    @PostMapping
    public Mono<SaleResponse> save(@RequestBody() SaleRequest saleRequest) {
        return service.save(mapper.map(saleRequest, SaleDto.class))
          .map(saleDto -> mapper.map(saleDto, SaleResponse.class));
    }
}
