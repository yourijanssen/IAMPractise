package com.appsdeveloperblog.ws.api.ResourceServer.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.*;

// import other necessary classes

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
      @Override
      public Collection<GrantedAuthority> convert(Jwt jwt) {
            System.out.println("Starting role conversion process");

            Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
            System.out.println("Realm access retrieved: " + realmAccess);

            if (realmAccess == null || realmAccess.isEmpty()) {
                  System.out.println("No roles found in realm_access.");
                  return new ArrayList<>();
            }

            List<String> roles = (List<String>) realmAccess.get("roles");
            System.out.println("Roles list from realm_access: " + roles);

            Collection<GrantedAuthority> returnValue = roles.stream()
                        .map(roleName -> "ROLE_" + roleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

            System.out.println("Granted Authorities after conversion: " + returnValue);
            return returnValue;
      }
}
