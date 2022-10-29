package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportSaleRequest {
    private String year;
    private List<MonthSaleReport> months;
}
