package com.bodega.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.bodega.api.shared.utils.Constants.API_TITLE;
import static com.bodega.api.shared.utils.Constants.API_VERSION;
import static com.bodega.api.shared.utils.Constants.API_DESCRIPTION;
import static com.bodega.api.shared.utils.Constants.CONTACT_NAME;
import static com.bodega.api.shared.utils.Constants.CONTACT_EMAIL;
import static com.bodega.api.shared.utils.Constants.LICENSE_NAME;
import static com.bodega.api.shared.utils.Constants.LICENSE_URL;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAppiConfig {
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(
						new Info()
								.title(API_TITLE)
								.version(API_VERSION)
								.description(API_DESCRIPTION)
								.contact(
										new Contact()
													.name(CONTACT_NAME)
													.email(CONTACT_EMAIL)
								)
								.license(
										new License()
													.name(LICENSE_NAME)
													.url(LICENSE_URL)
								)
				);
	}
}
