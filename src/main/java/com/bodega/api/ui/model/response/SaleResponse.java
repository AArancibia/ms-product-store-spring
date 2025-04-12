package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleResponse {
  private UUID id;
  private String code;
  private int salePrice;
  private LocalDateTime dateRegister;
  private List<SaleDetailResponse> saleDetail;
}
