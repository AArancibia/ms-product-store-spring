package com.bodega.api.ui.model.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
	private UUID id;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
