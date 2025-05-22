package com.appsdeveloperblog.ws.api.OAuthWebApp.controllers;

import java.security.Security;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;

import com.appsdeveloperblog.ws.api.OAuthWebApp.response.AlbumRest;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class Albumcontroller {

      @Autowired
      OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

      @Autowired
      WebClient webClient;

      @GetMapping("/albums")
      public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

            // Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            // OAuth2AuthenticationToken oathToken = (OAuth2AuthenticationToken)
            // authentication;

            // OAuth2AuthorizedClient oath2Client =
            // oAuth2AuthorizedClientService.loadAuthorizedClient(
            // oathToken.getAuthorizedClientRegistrationId(),
            // oathToken.getName());

            // String jwtAccessToken = oath2Client.getAccessToken().getTokenValue();
            // System.out.println("jwtAccessToken = " + jwtAccessToken);

            // System.out.println("Principal = " + principal);

            // OidcIdToken idToken = principal.getIdToken();
            // String idTokenValue = idToken.getTokenValue();
            // System.out.println("idTokenValue = " + idTokenValue);

            String url = "http://localhost:8091/albums";

            List<AlbumRest> returnedAlbums = webClient.get()
                        .uri(url)
                        .retrieve()
                        .bodyToFlux(AlbumRest.class)
                        .collectList()
                        .block();

            model.addAttribute("albums", returnedAlbums);
            System.out.println("returnedAlbums = " + returnedAlbums);

            // AlbumRest album = new AlbumRest();
            // album.setAlbumId("1");
            // album.setAlbumTitle("My Album");
            // album.setAlbumDescription("My Album Description");
            // album.setAlbumUrl("http://example.com/album.jpg");

            // AlbumRest album2 = new AlbumRest();
            // album2.setAlbumId("2");
            // album2.setAlbumTitle("My Album 2");
            // album2.setAlbumDescription("My Album 2 Description");
            // album2.setAlbumUrl("http://example.com/album2.jpg");

            // List returnedAlbums = Arrays.asList(album, album2);
            // model.addAttribute("albums", returnedAlbums);
            return "albums";
      }
}
