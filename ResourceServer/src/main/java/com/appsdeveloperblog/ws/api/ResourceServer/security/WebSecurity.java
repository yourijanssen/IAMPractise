package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

		// Print statement to verify setup
		System.out.println("JwtAuthenticationConverter setup with KeycloakRoleConverter");

		http
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/users/status/check")
				.hasRole("developer") // Ensure your token includes this role
				.anyRequest().authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter); // Ensure the converter is applied

		// Print statement to verify configuration applied
		System.out.println("Security configuration applied");
	}
}

// org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import
// org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
// // import org.springframework.web.cors.CorsConfiguration;
// // import org.springframework.web.cors.CorsConfigurationSource;
// // import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// // @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
// // @Configuration
// @EnableWebSecurity
// public class WebSecurity extends WebSecurityConfigurerAdapter {

// protected void configure(HttpSecurity http) throws Exception {

// JwtAuthenticationConverter jwtAuthenticationConverter = new
// JwtAuthenticationConverter();
// jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new
// KeycloakRoleConverter());

// http.cors().and()
// .authorizeRequests()
// .antMatchers(HttpMethod.GET, "/users/status/check")
// // .hasAuthority("SCOPE_profile")
// .hasRole("developer")
// // .hasAnyAuthority("ROLE_developer")
// // .hasAnyRole("devleoper","user")
// .anyRequest().authenticated()
// .and()
// .oauth2ResourceServer()
// .jwt()
// .jwtAuthenticationConverter(jwtAuthenticationConverter);
// }

// @Bean
// CorsConfigurationSource corsConfigurationSource() {
// CorsConfiguration corsConfiguration = new CorsConfiguration();
// corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
// corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
// corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// source.registerCorsConfiguration("/**", corsConfiguration);

// return source;
// }

// }
