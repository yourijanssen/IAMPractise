package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurity {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
		jwtAuthConverter
				.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

		System.out.println("JwtAuthenticationConverter setup with KeycloakRoleConverter");

		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/users/status/check")
						.hasRole("developer")
						.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2
						.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

		System.out.println("Security configuration applied");
		return http.build();
	}
}