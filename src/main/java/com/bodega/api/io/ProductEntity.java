package com.bodega.api.io;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "producto")
public class ProductEntity implements Serializable {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    public UUID id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "precio_unitario")
    private int unitPrice;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "imagen")
    private String image;

    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "categoria_id", columnDefinition = "uuid")
    private UUID categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<SaleDetailEntity> saleDetails;

}
