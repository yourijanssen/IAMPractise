package com.appsdeveloperblog.ws.api.OAuthWebApp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsdeveloperblog.ws.api.OAuthWebApp.response.AlbumRest;

@Controller
public class Albumcontroller {
      @GetMapping("/albums")
      public String getAlbums(Model model) {

            AlbumRest album = new AlbumRest();
            album.setAlbumId("1");
            album.setAlbumTitle("My Album");
            album.setAlbumDescription("My Album Description");
            album.setAlbumUrl("http://example.com/album.jpg");

            AlbumRest album2 = new AlbumRest();
            album2.setAlbumId("2");
            album2.setAlbumTitle("My Album 2");
            album2.setAlbumDescription("My Album 2 Description");
            album2.setAlbumUrl("http://example.com/album2.jpg");

            List returnedAlbums = Arrays.asList(album, album2);
            model.addAttribute("albums", returnedAlbums);
            return "albums";
      }
}
