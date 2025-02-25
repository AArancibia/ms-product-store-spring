package com.bodega.api.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(value = {"profiles"})
@Entity
@Table(name = "accesos")
public class ProfileEntity implements Serializable {
  @Id
  
  private UUID id;

  @Column(name = "ruta", nullable = false)
  private String url;

  @Column(name = "icono", nullable = false)
  private String icon;

  @Column(name = "descripcion", nullable = false)
  private String description;

  @Column(name = "general", nullable = false)
  private Boolean general;

  @OneToMany(mappedBy = "profile")
  private Set<UserProfileEntity> profiles;
}
