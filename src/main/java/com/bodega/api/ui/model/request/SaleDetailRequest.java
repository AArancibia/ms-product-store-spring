package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaleDetailRequest {
  private UUID id;
  private double price;
  private int quantity;
  private UUID productId;
}
