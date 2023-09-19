package com.bodega.api.repository;

import com.bodega.api.io.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {
  SaleEntity findAllByPaypalId(String paypalId);
}
