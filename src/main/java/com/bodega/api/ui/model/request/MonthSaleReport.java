package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthSaleReport {
    private String month;
    private int venta;
}