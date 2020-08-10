package com.fhoszowski.photogallery.controllers;

import com.fhoszowski.photogallery.models.Image;
import com.fhoszowski.photogallery.repositories.ImageRepository;
import com.fhoszowski.photogallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GalleryController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;


    @GetMapping("/gallery")
    public String path( Model model,Principal principal ) {

        model.addAttribute("imgs",imageService.getImagesLinksByUsername(principal));
        model.addAttribute("username",principal.getName());
        return "gallery";
    }

    @GetMapping("/sqlGallery")
    public String sqlGallery( Model model,Principal principal ) {

        List<Image> imagesByUsername = imageService.getImagesByUsername(principal);
        List<String> convertedImages = imageService.convertToBase64(imagesByUsername);

        model.addAttribute("username",principal.getName());
        model.addAttribute("imgs",convertedImages);
        return "sqlGallery";
    }


}
