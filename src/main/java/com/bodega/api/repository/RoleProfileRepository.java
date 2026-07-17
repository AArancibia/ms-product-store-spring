package com.bodega.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bodega.api.io.RoleProfileEntity;

@Repository
public interface RoleProfileRepository extends JpaRepository<RoleProfileEntity, UUID> {
	List<RoleProfileEntity> findByRoleId(UUID roleId);
	@Query(value = "SELECT * from getProfilesByRole(:roleName)", nativeQuery = true)
	List<RoleProfileEntity> findAllProfilesByRoleName(String roleName);
}
