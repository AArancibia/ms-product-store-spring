package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="detalleventa")
@JsonIgnoreProperties({ "sale", "product" })
public class SaleDetailEntity implements Serializable {
    @Id
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "precio")
    private int price;

    @Column(name = "cantidad")
    private int quantity;

    @Type(type="uuid-char")
    @Column(name = "venta_id", columnDefinition = "uuid")
    private UUID saleId;

    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleEntity sale;

    @Type(type="uuid-char")
    @Column(name = "producto_id", columnDefinition = "uuid")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;
}
