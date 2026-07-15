package com.bodega.api.io;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(value = {"parent", "role", "roleId", "profileId"})
@Table(name = "role_accesos")
public class RoleProfileEntity {
	
	@Id
	@Column()
	private UUID id;
	
	@Column(name = "role_id", columnDefinition = "uuid")
	  private UUID roleId;

	  @ManyToOne
	  @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	  private RoleEntity role;

	
	  @Column(name = "accesos_id", columnDefinition = "uuid")
	  private UUID profileId;

	  @ManyToOne
	  @JoinColumn(name = "accesos_id", referencedColumnName = "id", insertable = false, updatable = false)
	  private ProfileEntity profile;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "parent_id", nullable = true)
	  private RoleProfileEntity parent;

	  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	  private Set<RoleProfileEntity> children = new HashSet<>();
}
