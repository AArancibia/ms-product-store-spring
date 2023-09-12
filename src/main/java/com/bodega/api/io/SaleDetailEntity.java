package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="detalleventa")
@JsonIgnoreProperties({ "sale", "product" })
public class SaleDetailEntity implements Serializable {
    // @Type(type="uuid-char") postgres
    @Id
    private UUID id;

    @Column(name = "precio")
    private int price;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "venta_id") // columdefinition=uuid postgres
    private UUID saleId;

    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleEntity sale;

    @Column(name = "producto_id")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;
}
