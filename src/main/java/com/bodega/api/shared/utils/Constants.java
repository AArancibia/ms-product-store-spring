package com.bodega.api.shared.utils;

public final class Constants {
	public static String HTTP_FORBIDDEN_MESSAGE = "Your account does not have authorization for this action.";
	public static String HTTP_DEFAULT_MESSAGE = "Error has ocurred.";
	public static final String[] SWAGGER_WHITELIST = {
		"/swagger-ui.html",
		"/swagger-ui/**",
		"/api-docs/**",
		"/v3/api-docs/**",
		"/webjars/**"
	};
	public static final String API_TITLE = "OPEN API - BODEGA STORE API";
	public static final String API_VERSION = "4.1.0";
	public static final String API_DESCRIPTION = "API for Bodega Store app";
	public static final String CONTACT_NAME = "Alexis Arancibia";
	public static final String CONTACT_EMAIL = "alexis2396@hotmail.com";
	public static final String LICENSE_NAME = "Apache 2.0";
	public static final String LICENSE_URL = "http://springdoc.org";
}
