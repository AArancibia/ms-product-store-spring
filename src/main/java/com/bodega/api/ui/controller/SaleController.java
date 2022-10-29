package com.bodega.api.ui.controller;

import com.bodega.api.service.SaleService;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("ventas")
public class SaleController {
    private final SaleService service;

    @PostMapping("reporte")
    public void generateReport(@RequestBody() ReportSaleRequest saleDataReport, HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        this.service.buildReportSales(saleDataReport, response);
    }
}
