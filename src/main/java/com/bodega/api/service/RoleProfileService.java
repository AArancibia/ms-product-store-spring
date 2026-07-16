package com.bodega.api.service;

import com.bodega.api.shared.dto.RoleProfileDto;

import reactor.core.publisher.Flux;

public interface RoleProfileService {
	Flux<RoleProfileDto> findAllProfilesByRoleName(String roleName);
}
