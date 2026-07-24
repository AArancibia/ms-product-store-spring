package com.bodega.api.ui.controller;

import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.CreateOrderRequest;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
import com.bodega.api.ui.model.response.PaypalCreateOrderResponse;
import com.bodega.api.ui.model.response.ReportSaleResponse;
import com.bodega.api.ui.model.response.SaleResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
@Tag(name = "Sales", description = "Endpoint methods for sales feature")
@RequiredArgsConstructor
@RestController
@RequestMapping("sale")
public class SaleController {
    private final SaleService service;
    private final ModelMapper mapper;

    @Operation(summary = "Get sales by user id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Retrieve sales of user"),
    		@ApiResponse(responseCode = "400", description = "No sales found")
    })
    @GetMapping("/user/{id}")
    public Flux<SaleResponse> getSalesByUser(@PathVariable("id") UUID userId) {
        return service.getSalesByUser(userId)
          .map(sale -> mapper.map(sale, SaleResponse.class));
    }

    @Operation(summary = "Create order Paypal")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Paypal order created"),
    })
    @PostMapping("order")
    public Mono<PaypalCreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @Operation(summary = "Capture the Paypal order with the id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Order captured"),
    })
    @PostMapping("order/{id}/capture")
    public Mono<PaypalCreateOrderResponse> captureOrder(@PathVariable("id") String paypalId, @RequestBody SaleRequest request) {
        return service.captureOrder(paypalId, request)
          .flatMap(paypalCreateOrderResponse -> service.updateProductStockByPaypalId(paypalId)
            .collectList()
            .single()
            .map(productDto -> paypalCreateOrderResponse));
    }

    @Operation(summary = "Generate report file")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Created report file"),
    })
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

    @Operation(summary = "Generate sales report")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Sales quantity pear month and year"),
    })
    @PostMapping("reporte")
    public List<ReportSaleResponse> generateReport() {
        return this.service.generateReportSales();
    }

    @Operation(summary = "Save new sale")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Saved sale"),
    })
    @PostMapping
    public Mono<SaleResponse> save(@Valid @RequestBody SaleRequest saleRequest) {
        return service.save(mapper.map(saleRequest, SaleDto.class))
          .map(saleDto -> mapper.map(saleDto, SaleResponse.class));
    }
}
