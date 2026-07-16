package com.bodega.api.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Set<ProfileDto> profiles;
}
