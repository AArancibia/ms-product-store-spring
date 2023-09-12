package com.bodega.api.io;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class ProductEntity implements Serializable {

    @Id
    public UUID id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "precio_unitario")
    private double unitPrice;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "imagen")
    private String image;

    // @Type(type="uuid-char")
    @Column(name = "categoria_id")
    private UUID categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<SaleDetailEntity> saleDetails;

}
