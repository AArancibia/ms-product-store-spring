package com.bodega.api.io;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable {
    @Id
    @Type(type="uuid-char")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario")
    private String username;

    @Column(name = "nombres")
    private String givenName;

    @Column(name = "apepat")
    private String lastName;

    @Column(name = "apemat")
    private String surname;

    @Column(name = "telefono")
    private String telephone;

}
