package com.bodega.api.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bodega.api.repository.RoleProfileRepository;
import com.bodega.api.service.RoleProfileService;
import com.bodega.api.shared.dto.RoleProfileDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class RoleProfileServImpl implements RoleProfileService {
	
	private final RoleProfileRepository roleProfileRepository;
	private final ModelMapper mapper;

	@Transactional(readOnly = true)
	@Override
	public Flux<RoleProfileDto> findAllProfilesByRoleName(String roleName) {
		var profiles = roleProfileRepository.findAllProfilesByRoleName(roleName)
				.stream()
				.map(roleProfile -> mapper.map(roleProfile, RoleProfileDto.class))
				.toList();
		return Flux.fromIterable(profiles);
	}

}
