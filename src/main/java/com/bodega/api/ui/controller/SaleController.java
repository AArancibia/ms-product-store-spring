package com.bodega.api.ui.controller;

import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import com.bodega.api.ui.model.response.SaleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("sale")
public class SaleController {
    private final SaleService service;
    private final ModelMapper mapper;

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

    @PostMapping("reporte/pdf")
    public void generateReportPDF(@RequestBody() ReportSaleRequest saleDataReport, HttpServletResponse response) {
        response.setContentType("application/pdf");
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
