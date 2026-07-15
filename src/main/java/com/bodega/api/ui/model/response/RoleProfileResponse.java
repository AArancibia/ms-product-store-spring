package com.bodega.api.ui.model.response;

import java.util.Set;

import com.bodega.api.io.RoleProfileEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleProfileResponse {
	private ProfileResponse profile;
	private Set<RoleProfileEntity> children;
}
