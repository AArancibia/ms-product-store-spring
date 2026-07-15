package com.bodega.api.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodega.api.service.RoleProfileService;
import com.bodega.api.ui.model.response.RoleProfileResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {
	private final RoleProfileService roleProfileService;
	private final ModelMapper mapper;
	
	@GetMapping("/{role}/accesos")
	  public ResponseEntity<Flux<RoleProfileResponse>> findProfilesByRole(@PathVariable String role) {
		return ResponseEntity
				.ok(
						roleProfileService.findAllProfilesByRoleName(role)
						.map(profile -> mapper.map(profile, RoleProfileResponse.class))
					);
	  }
}
