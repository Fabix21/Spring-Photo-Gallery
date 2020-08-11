package com.fhoszowski.photogallery.controllers;

import com.fhoszowski.photogallery.models.Image;
import com.fhoszowski.photogallery.repositories.ImageRepository;
import com.fhoszowski.photogallery.services.GalleryService;
import com.fhoszowski.photogallery.services.ImageService;
import com.fhoszowski.photogallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GalleryController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @Autowired
    GalleryService galleryService;

    @GetMapping("/availableGalleries")
    public String userAvailableGalleries( Model model,Principal principal ) {
        List<String> availableGalleries = userService.getUser(principal.getName())
                                                     .getGalleries()
                                                     .stream()
                                                     .map(gallery -> gallery.getGalleryname())
                                                     .collect(Collectors.toList());

        model.addAttribute("availableGalleries",availableGalleries);
        model.addAttribute("username",principal.getName());
        return "availableGalleries";
    }

    @GetMapping("/gallery/{galleryName}")
    public String path( Model model,Principal principal,@PathVariable("galleryName") String selectedGallery ) {

        List<String> galleries = galleryService.getGalleriesNamesByUsername(principal.getName());
        model.addAttribute("imgs",galleries);
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
