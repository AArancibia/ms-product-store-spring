package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categoria")
@JsonIgnoreProperties({ "products" })
public class CategoryEntity implements Serializable {
    @Id
    private UUID id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

}
