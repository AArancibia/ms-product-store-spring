package com.bodega.api.service;

import com.bodega.api.ui.model.request.ReportSaleRequest;

import javax.servlet.http.HttpServletResponse;

public interface SaleService {
    void buildReportSales(ReportSaleRequest saleDataReport, HttpServletResponse response);
}
