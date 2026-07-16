package com.bodega.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodega.api.io.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
	RoleEntity findByName(String role);
}
