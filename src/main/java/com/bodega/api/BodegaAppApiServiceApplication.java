package com.bodega.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BodegaAppApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodegaAppApiServiceApplication.class, args);
	}

}
