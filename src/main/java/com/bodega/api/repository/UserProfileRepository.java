package com.bodega.api.repository;

import com.bodega.api.io.UserProfileEntity;
import com.bodega.api.io.UserProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UserProfileId> {
  List<UserProfileEntity> findAllByUserId(UUID userId);
}
