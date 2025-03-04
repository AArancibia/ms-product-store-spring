package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
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
  private UUID id;

  @Column(name = "nombres", nullable = false)
  String givenName;

  @Column(name = "apellido_paterno", nullable = false)
  String lastName;

  @Column(name = "apellido_materno")
  String surname;

  @Column(name = "username", unique = true)
  String username;

  @Email
  @Column(name = "email", unique = true)
  String email;

  @JsonIgnore
  @Column
  String password;

  @Column(name = "telefono")
  String telephone;

  @Column(name = "isGoogleAccount")
  Boolean isGoogleAccount;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdDate;

  @UpdateTimestamp
  private LocalDateTime updatedDate;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<SaleEntity> sales;

  @OneToMany(mappedBy = "user")
  Set<UserProfileEntity> profiles;
}
