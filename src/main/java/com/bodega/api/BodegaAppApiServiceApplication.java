package com.bodega.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class BodegaAppApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodegaAppApiServiceApplication.class, args);
	}

}
