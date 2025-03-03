package com.bodega.api.repository;

import com.bodega.api.io.RoleEntity;
import com.bodega.api.shared.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
  Optional<RoleEntity> findByRole(AppRole role);
}
