package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
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
@Table(name = "customers")
public class UserEntity implements Serializable {
  @Id
  private UUID id;

  @Column(name = "nombres", nullable = false)
  private String givenName;

  @Column(name = "apellido_paterno", nullable = false)
  private String lastName;

  @Column(name = "apellido_materno")
  private String surname;

  @Column(name = "username", unique = true)
  private String username;

  @Email
  @Column(name = "email", unique = true)
  private String email;

  @JsonIgnore
  @Column
  private String password;

  @Column
  private String role;

  @Column(name = "telefono")
  private String telephone;

  @Column(name = "isGoogleAccount")
  private Boolean isGoogleAccount;

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
