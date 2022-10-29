package com.bodega.api.io;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="venta")
public class SaleEntity {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "precio_venta") // todo in ms-product
    private int salePrice;

    @Column(name = "fecha_venta")  // todo in ms-product
    private Date dateRegister;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private List<SaleDetailEntity> saleDetails;

}
