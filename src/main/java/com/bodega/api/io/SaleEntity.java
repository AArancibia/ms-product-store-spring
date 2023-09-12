package com.bodega.api.io;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="venta")
public class SaleEntity implements Serializable {
    @Id
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "precio_venta")
    private int salePrice;

    @Column(name = "fecha_venta")
    private LocalDateTime dateRegister;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private List<SaleDetailEntity> saleDetail;

}
