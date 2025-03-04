package com.bodega.api.repository;

import com.bodega.api.io.UserEntity;
import com.bodega.api.shared.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  UserDto findByUsername(String username);
}
