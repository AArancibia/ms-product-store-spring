package com.bodega.api.shared.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.bodega.api.io.RoleProfileEntity;

import lombok.Data;


@Data
public class RoleProfileDto implements Serializable {
	private UUID roleId;
	private RoleDto role;
	private UUID profileId;
	private ProfileDto profile;
	private Set<RoleProfileEntity> children;
}
