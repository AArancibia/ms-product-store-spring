package com.bodega.api.io;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;

public abstract class Audit {
    @CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(updatable = false)
	private LocalDateTime updatedDate;
}
