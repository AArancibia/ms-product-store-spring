package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Getter
@Setter
public class SaleRequest {
  @NotNull(message = "Sale ID is required")
  private UUID id;
  private String code;
  @NotNull(message = "User ID is required")
  private UUID userId;
  @Min(value = 1, message = "Sale price must not be 0")
  private int salePrice;
  private LocalDateTime dateRegister;
  @Size(min = 1, message = "At least one sale must be register")
  private List<SaleDetailRequest> saleDetail;
}
