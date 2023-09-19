package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SaleRequest {
  private UUID id;
  private int salePrice;
  private LocalDateTime dateRegister;
  private List<SaleDetailRequest> saleDetail;
}
