package com.bodega.api.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodega.api.service.ProfileService;
import com.bodega.api.service.RoleProfileService;
import com.bodega.api.ui.model.response.ProfileResponse;
import com.bodega.api.ui.model.response.RoleProfileResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Tag(name = "Role", description = "Endpoint methods for role feature")
@RequiredArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {
	private final RoleProfileService roleProfileService;
	private final ProfileService profileService;
	private final ModelMapper mapper;
	
	@Operation(summary = "List of general profiles")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Get all general profiles"),
    		@ApiResponse(responseCode = "400", description = "No general profiles found")
    })
	 @GetMapping("/accesos/general")
	  Flux<RoleProfileResponse> getGeneralProfiles() {
	    return profileService.getGeneralProfiles()
	      .map(profile -> {
	    	  var roleProfile = new RoleProfileResponse();
	    	  roleProfile.setProfile(mapper.map(profile, ProfileResponse.class));
	    	  return roleProfile;
	      });
	  }
	
	@Operation(summary = "List of profiles by role")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Get all profiles by role"),
    		@ApiResponse(responseCode = "400", description = "No profiles found")
    })
	@GetMapping("/{role}/accesos")
	  public ResponseEntity<Flux<RoleProfileResponse>> findProfilesByRole(@PathVariable String role) {
		return ResponseEntity
				.ok(
						roleProfileService.findAllProfilesByRoleName(role)
						.map(profile -> mapper.map(profile, RoleProfileResponse.class))
					);
	  }
}
