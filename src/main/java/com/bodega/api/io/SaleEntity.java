package com.bodega.api.io;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="venta")
public class SaleEntity implements Serializable {
    @Id
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "precio_venta")
    private int salePrice;

    @Column(name = "fecha_venta")
    private LocalDateTime dateRegister;

    @Column(name = "paypal_id")
    private String paypalId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale",  fetch = FetchType.EAGER)
    private List<SaleDetailEntity> saleDetail;

}
