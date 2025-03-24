package com.bodega.api.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SaleDetailDto {
  private UUID id;
  private double price;
  private int quantity;
  private UUID saleId;
  private UUID productId;
  private ProductDto product;
}
