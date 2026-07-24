package com.bodega.api.io;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(value = {"profiles"})
@Table(name = "role")
public class RoleEntity implements Serializable {

	@Id
	private UUID id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column()
	private String description;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(updatable = false)
	private LocalDateTime updatedDate;
	
	@OneToMany(mappedBy = "role")
	private Set<RoleProfileEntity> profiles;
}
