package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="detalleventa")
@JsonIgnoreProperties({ "sale", "product" })
public class SaleDetailEntity implements Serializable {
    @Id
    
    private UUID id;

    @Column(name = "precio")
    private int price;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "venta_id", columnDefinition = "uuid")
    private UUID saleId;

    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleEntity sale;

    
    @Column(name = "producto_id", columnDefinition = "uuid")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;
}
