package com.appsdeveloperblog.clients.sociallogin.SocialLoginWebClient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

      @Autowired
      ClientRegistrationRepository clientRegistrationRepository;

      @Bean
      SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
            http
                        .authorizeHttpRequests(auth -> auth
                                    .requestMatchers("/").permitAll()
                                    .anyRequest().authenticated())
                        .oauth2Login(oauth2 -> oauth2
                                    .clientRegistrationRepository(clientRegistrationRepository)
                                    .defaultSuccessUrl("/home", true))
                        .logout(logout -> logout
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true)
                                    .clearAuthentication(true)
                                    .deleteCookies("JSESSIONID"));

            return http.build();
      }
}