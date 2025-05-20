package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.api.ResourceServer.response.UserRest;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping("/status/check")
	public String status() {
		System.out.println("Status endpoint called");
		return "Working..";
	}

	@PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
	// @Secured("ROLE_developer")
	@DeleteMapping(path = "/{id}")
	public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		System.out.println("DeleteUser endpoint called with id: " + id);
		System.out.println("JWT subject: " + jwt.getSubject());

		if (id.equals(jwt.getSubject())
				|| ((List<String>) jwt.getClaim("roles")).contains("ROLE_developer")) {
			System.out.println("Authorization checks passed");
		} else {
			System.out.println("Authorization checks failed");
		}

		return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
	}

	@PostAuthorize("returnObject.userId == #jwt.subject")
	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		System.out.println("GetUser endpoint called with id: " + id);
		System.out.println("JWT subject: " + jwt.getSubject());

		UserRest returnValue = new UserRest("Youri", "Janssen", "e2c07010-09d2-4dc1-ad52-9348ce8fb5ec");

		System.out.println("ReturnObject userId: " + returnValue.getUserId());

		return returnValue;
	}
}

// @Autowired
// Environment env;w
// @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
// // @Secured("ROLE_developer")
// @DeleteMapping(path = "/{id}")
// public String deleteUser(@PathVariable String id, @AuthenticationPrincipal
// Jwt jwt) {
// return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
// }

// @PostAuthorize("returnObject.userId == #jwt.subject")
// @GetMapping(path = "/{id}")
// public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt
// jwt) {
// return new UserRest("Youri", "Janssen",
// "5f3fb480-f86c-4514-8d23-ca88d66c6253");
// }
