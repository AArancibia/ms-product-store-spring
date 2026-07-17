package com.bodega.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@SpringBootApplication
public class BodegaAppApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodegaAppApiServiceApplication.class, args);
	}

}
