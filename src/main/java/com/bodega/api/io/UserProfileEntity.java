package com.bodega.api.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(UserProfileId.class)
@Table(name = "usuarios_accesos")
public class UserProfileEntity implements Serializable {

  @Id
  @Column(name = "usuario_id", columnDefinition = "uuid")
  private UUID userId;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
  private UserEntity user;

  @Id
  @Column(name = "accesos_id", columnDefinition = "uuid")
  private UUID profileId;

  @ManyToOne
  @JoinColumn(name = "accesos_id", referencedColumnName = "id", insertable = false, updatable = false)
  private ProfileEntity profile;

  public UserProfileEntity(UUID userId, UUID profileId) {
    this.userId = userId;
    this.profileId = profileId;
  }
}
