package com.bodega.api.ui.controller;

import com.bodega.api.service.SaleService;
import com.bodega.api.shared.dto.SaleDto;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import com.bodega.api.ui.model.request.SaleRequest;
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

    @GetMapping("/user/{id}")
    public Flux<SaleResponse> getSalesByUserId(@PathVariable UUID id) {
        return service.getSalesByUserId(id)
          .map(saleDto -> mapper.map(saleDto, SaleResponse.class));
    }
}
