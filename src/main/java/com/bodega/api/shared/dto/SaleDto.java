package com.bodega.api.shared.dto;

import com.bodega.api.io.SaleDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleDto {
    private UUID id;
    private String code;
    private int salePrice;
    private Date dateRegister;
    // private List<SaleDetailEntity> saleDetails;
}
