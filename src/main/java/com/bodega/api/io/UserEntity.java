package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(value = {"profiles"})
@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable {
  @Id
  @Type(type="uuid-char")
  private UUID id;

  @Column(name = "nombres", nullable = false)
  String givenName;

  @Column(name = "apellido_paterno")
  String lastName;

  @Column(name = "apellido_materno", nullable = false)
  String surname;

  @Column(name = "telefono", nullable = false)
  String telephone;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<SaleEntity> sales;

  @OneToMany(mappedBy = "user")
  Set<UserProfileEntity> profiles;
}
