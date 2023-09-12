package com.bodega.api.io;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UserEntity {
  @Id
  private UUID id;

  @Column(name = "usuario", length = 25)
  private String username;

  @Column(name = "nombres", length = 50)
  private String givenName;

  @Column(name = "apepaterno", length = 50)
  private String lastName;

  @Column(name = "apematerno", length = 50)
  private String surname;

  @Column(name = "telephone", length = 20)
  private String telephone;

  @Column(name = "correo", length = 45)
  private String email;

  @Column(name = "contrasenia", length = 200)
  private String password;

  @Column(name = "role", length = 45)
  private String rol;
}
