package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SaleDto {
    private UUID id;
    private String code;
    private int salePrice;
    private LocalDateTime dateRegister;
    private List<SaleDetailDto> saleDetail;
}
