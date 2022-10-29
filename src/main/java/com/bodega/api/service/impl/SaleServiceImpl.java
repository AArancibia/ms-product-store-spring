package com.bodega.api.service.impl;

import com.bodega.api.repository.SaleRepository;
import com.bodega.api.service.SaleService;
import com.bodega.api.shared.report.SaleReport;
import com.bodega.api.ui.model.request.ReportSaleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;

    @Override
    public void buildReportSales(ReportSaleRequest saleDataReport, HttpServletResponse response) {
        SaleReport.BuildReport(saleDataReport, response);
    }
}
