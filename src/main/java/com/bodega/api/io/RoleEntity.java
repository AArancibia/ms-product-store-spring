package com.bodega.api.io;

import com.bodega.api.shared.enums.AppRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {
  @Id
  private UUID id;

  @ToString.Exclude
  @Column
  @Enumerated(EnumType.STRING)
  private AppRole role;

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonBackReference
  @ToString.Exclude
  private Set<UserEntity> users = new HashSet<>();
}
