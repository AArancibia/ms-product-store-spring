package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportSaleResponse {
  String year;
  List<ReportMonthSales> months;

  @Getter
  @Setter
  public static class ReportMonthSales {
    String month;
    int venta;
  }
}
