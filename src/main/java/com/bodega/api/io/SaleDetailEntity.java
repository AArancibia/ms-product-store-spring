package com.bodega.api.io;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="detalleventa")
public class SaleDetailEntity {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private String id;

    @Column(name = "precio")
    private int price;

    @Column(name = "cantidad")
    private int quantity;

    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "venta_id", columnDefinition = "uuid")
    private UUID saleId;

    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleEntity sale;

    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "producto_id", columnDefinition = "uuid")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;
}
