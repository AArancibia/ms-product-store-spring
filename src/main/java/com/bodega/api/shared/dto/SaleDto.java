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
    private UUID userId;
    private int salePrice;
    private String paypalId;
    private LocalDateTime dateRegister;
    private List<SaleDetailDto> saleDetail;
}
