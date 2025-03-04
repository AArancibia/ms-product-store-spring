package com.bodega.api.io;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuarios_accesos")
public class UserProfileEntity implements Serializable {

  @Id
  @Column(name = "usuario_id", columnDefinition = "uuid")
  private UUID userId;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private UserEntity user;

  @Id

  @Column(name = "accesos_id", columnDefinition = "uuid")
  private UUID profileId;

  @ManyToOne
  @JoinColumn(name = "accesos_id", referencedColumnName = "id")
  private ProfileEntity profile;
}
