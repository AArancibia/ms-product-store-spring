package com.bodega.api.shared.dto;

import java.util.UUID;

import com.bodega.api.shared.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class ShipmentDto {
    private UUID id;
    private UUID orderId;
    private OrderStatus status;
    private String trackingNumber;
}
