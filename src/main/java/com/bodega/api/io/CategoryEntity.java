package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @Type(type="uuid-char")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

}
