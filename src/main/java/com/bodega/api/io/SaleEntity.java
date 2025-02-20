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

    @Column(name = "code")
    private String code;

    @Column(name = "precio_venta")
    private int salePrice;

    @Column(name = "fecha_venta")
    private LocalDateTime dateRegister;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private List<SaleDetailEntity> saleDetail;

    @Type(type="uuid-char")
    @Column(name = "usuario_id", columnDefinition = "uuid")
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

}
