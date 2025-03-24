package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaleDetailResponse {
  private UUID id;
  private double price;
  private int quantity;
  private UUID saleId;
  private UUID productId;
  private ProductResponse product;
}
