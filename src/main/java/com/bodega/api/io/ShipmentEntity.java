package com.bodega.api.io;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.bodega.api.shared.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@Entity
@Table(name = "shipment")
public class ShipmentEntity extends Audit implements Serializable{
    @Id 
    private UUID id;

    @Column(name = "order_id", columnDefinition = "uuid")
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleEntity order;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false)
    private OrderStatus status;

    @Pattern(
            regexp="^[A-Za-z]{2}\\d{9}[A-Za-z]{2}$",
            message="Tracking number with invalid format"
        )
    @Column(name = "tracking_number", length = 13)
    private String trackingNumber;
}
